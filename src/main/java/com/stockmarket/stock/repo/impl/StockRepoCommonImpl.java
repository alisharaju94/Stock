package com.stockmarket.stock.repo.impl;

import java.io.File;
import java.io.Writer;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.stockmarket.stock.contants.CommonConstants;
import com.stockmarket.stock.entity.StockDetails;
import com.stockmarket.stock.entity.StockEntity;
import com.stockmarket.stock.model.StockRangeQueryParams;
import com.stockmarket.stock.repo.intf.StockRepository;

@Component
public class StockRepoCommonImpl {

	@Autowired
	private StockRepository stockCommonOpRepo;

	@Autowired
	private CassandraOperations template;

	public StockEntity insertStock(StockEntity entity) {
		entity = stockCommonOpRepo.save(entity);
		long count = stockCommonOpRepo.count();
		checkForBackUp(count);
		return entity;

	}

	public StockDetails getStockForRange(StockRangeQueryParams params) {
		StockDetails stockDetails = new StockDetails();
		String query = " SELECT * FROM stock WHERE company_code = " + params.getCompanyCode() + " and time_stamp >= '"
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
				File file = new File("/stock/data/Stock_backup_" + CommonConstants.today()+ ".csv");
				file.createNewFile();
				Writer writer = Files.newBufferedWriter(file.toPath());
				StatefulBeanToCsv<StockEntity> beanToCsv = new StatefulBeanToCsvBuilder<StockEntity>(writer).build();
				beanToCsv.write(entities);
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteStocks(String companyCode) {
		Select selectQuery = QueryBuilder.selectFrom("stock").all()
				.where(Relation.column("com_code").isEqualTo(QueryBuilder.literal(companyCode)));
		List<StockEntity> entities = template.select(selectQuery.asCql(), StockEntity.class);
		stockCommonOpRepo.deleteAll(entities);
	}

}
