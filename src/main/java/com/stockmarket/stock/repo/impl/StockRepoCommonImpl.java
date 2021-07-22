package com.stockmarket.stock.repo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.stockmarket.stock.entity.StockDetails;
import com.stockmarket.stock.entity.StockEntity;
import com.stockmarket.stock.model.StockRangeQueryParams;
import com.stockmarket.stock.repo.intf.StockRepository;

@Component
public class StockRepoCommonImpl {

	@Autowired
	private StockRepository stockCommonOpRepo;


	public StockEntity insertStock(StockEntity entity) {
		entity = stockCommonOpRepo.save(entity);
//		long count = stockCommonOpRepo.count();
//		checkForBackUp(count);
		return entity;

	}

	public StockDetails getStockForRange(StockRangeQueryParams params) {
		StockDetails stockDetails = new StockDetails();
//		String query = " SELECT * FROM stock WHERE com_code = '" + params.getCompanyCode() + "' and time_stamp >= '"
//				+ params.getStart() + "' and time_stamp <= '" + params.getEnd() + "' allow filtering";
//		List<StockEntity> entities = template.select(query, StockEntity.class);
//		stockDetails.setStocks(entities);
		return stockDetails;

	}

//	private void checkForBackUp(long count) {
//		if (count % 3 == 0) {
//			Select slectQuery = QueryBuilder.selectFrom("stock").all().limit(3);
//			List<StockEntity> entities = template.select(slectQuery.asCql(), StockEntity.class);
//			try {
//				File file = new File("/stock/data/Stock_backup_" + CommonConstants.today() + ".csv");
//				file.createNewFile();
//				Writer writer = Files.newBufferedWriter(file.toPath());
//				StatefulBeanToCsv<StockEntity> beanToCsv = new StatefulBeanToCsvBuilder<StockEntity>(writer).build();
//				beanToCsv.write(entities);
//				writer.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

	public void deleteStocks(String comCode) {
		List<StockEntity> entities = getAllStocks(comCode);
		stockCommonOpRepo.deleteAll(entities);
	}

	public StockDetails getStocksOfCompany(String comCode) {
		StockDetails details = new StockDetails();
		List<StockEntity> entities = getAllStocks(comCode);
		details.setStocks(entities);
		return details;
	}

	private List<StockEntity> getAllStocks(String comCode) {
//		Select selectQuery = QueryBuilder.selectFrom("stock").all()
//				.where(Relation.column("com_code").isEqualTo(QueryBuilder.literal(comCode)));
//		return template.select(selectQuery.asCql(), StockEntity.class);
		return null;
	}

}
