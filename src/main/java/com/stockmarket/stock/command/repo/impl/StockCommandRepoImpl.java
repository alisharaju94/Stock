package com.stockmarket.stock.command.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
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

}
