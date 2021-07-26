package com.stockmarket.stock.query.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stockmarket.stock.command.entity.StockEntity;
import com.stockmarket.stock.common.constants.CommonConstants;
import com.stockmarket.stock.query.entity.StockPrimaryKey;
import com.stockmarket.stock.query.repo.impl.StockQueryRepoImpl;

@Component
public class KafkaListener {

	@Autowired
	private StockQueryRepoImpl stockQueryRepoImpl;

	@org.springframework.kafka.annotation.KafkaListener(topics = CommonConstants.KAFKA_TOPIC, groupId = CommonConstants.KAFKA_LISTENER_GROUP_ID)
	public void consume(StockEntity commandEntity) throws IllegalAccessException, InvocationTargetException {
		com.stockmarket.stock.query.entity.StockEntity queryEntity = new com.stockmarket.stock.query.entity.StockEntity();
		queryEntity.setPrice(commandEntity.getPrice());
		queryEntity.setCurrencyCode(commandEntity.getCurrencyCode());
		StockPrimaryKey primaryKey = new StockPrimaryKey();
		primaryKey.setComCode(commandEntity.getComCode());
		primaryKey.setTimeStamp(commandEntity.getTimeStamp());
		queryEntity.setPrimaryKey(primaryKey);
		stockQueryRepoImpl.insertStock(queryEntity);
	}
}
