package com.stockmarket.stock.command.repo.impl;

import java.io.File;
import java.io.Writer;
import java.nio.file.Files;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.stockmarket.stock.command.repo.intf.StockCommandRepository;
import com.stockmarket.stock.common.constants.CommonConstants;
import com.stockmarket.stock.command.entity.StockEntity;

@Component
public class StockCommandRepoImpl {

	@Autowired
	private StockCommandRepository stockCommandRepo;

	@Autowired
	private KafkaTemplate<String, StockEntity> kafkaTemplate;

	public StockEntity insertStock(StockEntity entity) {
		
		entity = stockCommandRepo.save(entity);
		kafkaTemplate.send(CommonConstants.KAFKA_TOPIC, entity);
		return entity;
	}

//	public void deleteStocks(String comCode) {
//		List<StockEntity> entities = getAllStocks(comCode);
//		stockCommandRepo.deleteAll(entities);
//	}

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
//
//	public StockDetails getStocksOfCompany(String comCode) {
//		StockDetails details = new StockDetails();
//		List<StockEntity> entities = getAllStocks(comCode);
//		details.setStocks(entities);
//		return details;
//	}
//
//	private List<StockEntity> getAllStocks(String comCode) {
//		Select selectQuery = QueryBuilder.selectFrom("stock").all()
//				.where(Relation.column("com_code").isEqualTo(QueryBuilder.literal(comCode)));
//		return template.select(selectQuery.asCql(), StockEntity.class);
//	}

}
