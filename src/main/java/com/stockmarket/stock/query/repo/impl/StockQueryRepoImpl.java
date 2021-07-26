package com.stockmarket.stock.query.repo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.stockmarket.stock.query.entity.StockDetails;
import com.stockmarket.stock.query.entity.StockEntity;
import com.stockmarket.stock.query.model.StockRangeQueryParams;
import com.stockmarket.stock.query.repo.intf.StockQueryRepository;

@Component
public class StockQueryRepoImpl {

	@Autowired
	private StockQueryRepository stockQueryRepo;

	@Autowired
	private CassandraOperations template;

	public StockEntity insertStock(StockEntity entity) {
		entity = stockQueryRepo.save(entity);
		// long count = stockCommonOpRepo.count();
		// checkForBackUp(count);
		return entity;

	}

	public StockDetails getStockForRange(StockRangeQueryParams params) {
		StockDetails stockDetails = new StockDetails();
		String query = " SELECT * FROM stock WHERE com_code = '" + params.getCompanyCode() + "' and time_stamp >= '"
				+ params.getStart() + "' and time_stamp <= '" + params.getEnd() + "' allow filtering";
		List<StockEntity> entities = template.select(query, StockEntity.class);
		stockDetails.setStocks(entities);
		return stockDetails;

	}

	private void checkForBackUp(long count) {
		if (count % 3 == 0) {
			Select slectQuery = QueryBuilder.selectFrom("stock").all().limit(3);
			List<StockEntity> entities = template.select(slectQuery.asCql(), StockEntity.class);
			try {
//				File file = new File("/stock/data/Stock_backup_" + CommonConstants.today() + ".csv");
//				file.createNewFile();
//				Writer writer = Files.newBufferedWriter(file.toPath());
//				StatefulBeanToCsv<StockEntity> beanToCsv = new StatefulBeanToCsvBuilder<StockEntity>(writer).build();
//				beanToCsv.write(entities);
//				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteStocks(String comCode) {
		List<StockEntity> entities = getAllStocks(comCode);
		stockQueryRepo.deleteAll(entities);
	}

	public StockDetails getStocksOfCompany(String comCode) {
		StockDetails details = new StockDetails();
		List<StockEntity> entities = getAllStocks(comCode);
		details.setStocks(entities);
		return details;
	}
	
	public StockEntity getLatestStock(String companyCode) {
		Select selectQuery = QueryBuilder.selectFrom("stock").all()
				.where(Relation.column("com_code").isEqualTo(QueryBuilder.literal(companyCode))).perPartitionLimit(1);
		return template.select(selectQuery.asCql(), StockEntity.class).get(0);
	}

	private List<StockEntity> getAllStocks(String comCode) {
		Select selectQuery = QueryBuilder.selectFrom("stock").all()
				.where(Relation.column("com_code").isEqualTo(QueryBuilder.literal(comCode)));
		return template.select(selectQuery.asCql(), StockEntity.class);
	}

}
