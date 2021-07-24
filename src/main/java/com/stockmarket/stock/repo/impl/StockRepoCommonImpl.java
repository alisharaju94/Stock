package com.stockmarket.stock.repo.impl;

import java.io.File;
import java.io.Writer;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.Select;
import com.google.common.collect.Maps;
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
	private AmazonDynamoDB dynamoDBClient;

	public StockEntity insertStock(StockEntity entity) {
		entity = stockCommonOpRepo.save(entity);
	//	long count = stockCommonOpRepo.count();
	//	checkForBackUp(count);
		return entity;

	}

	public StockDetails getStockForRange(StockRangeQueryParams params) {
		StockDetails stockDetails = new StockDetails();
		Map<String, Condition> conditions = Maps.newHashMap();
		conditions.put(CommonConstants.DYNAMO_SORT_KEY,
				new Condition().withComparisonOperator(ComparisonOperator.BETWEEN).withAttributeValueList(
						new AttributeValue().withN(String.valueOf(params.getStart())),
						new AttributeValue().withN(String.valueOf(params.getEnd()))));
		StockEntity entity = new StockEntity();
		entity.setComCode(params.getCompanyCode());
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);
		DynamoDBQueryExpression<StockEntity> queryExpression = new DynamoDBQueryExpression<StockEntity>()
				.withHashKeyValues(entity).withRangeKeyConditions(conditions);
		List<StockEntity> entities = mapper.query(StockEntity.class, queryExpression);
		stockDetails.setStocks(entities);
		return stockDetails;

	}

//	private void checkForBackUp(long count) {
//		if (count % 3 == 0) {
//			List<StockEntity> entities = getLatest1000Records();
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

//	private List<StockEntity> getLatest1000Records() {
//		StockEntity entity = new StockEntity();
//		entity.setComCode("*");
//		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);
//		Map<String, Condition> conditions = Maps.newHashMap();
//		conditions.put("com_code", new Condition().withComparisonOperator(ComparisonOperator.BEGINS_WITH)
//				.withAttributeValueList(new AttributeValue().withS("C_")));
//		DynamoDBQueryExpression<StockEntity> queryExpression = new DynamoDBQueryExpression<StockEntity>()
//				.withHashKeyValues(entity)
//				.withQueryFilter(conditions)
//				.withScanIndexForward(false).withLimit(3);
//		return mapper.query(StockEntity.class, queryExpression);
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
		StockEntity entity = new StockEntity();
		entity.setComCode(comCode);
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);
		DynamoDBQueryExpression<StockEntity> queryExpression = new DynamoDBQueryExpression<StockEntity>()
				.withHashKeyValues(entity);
		return mapper.query(StockEntity.class, queryExpression);
	}

}
