package com.stockmarket.stock.command.mapper;

import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.stereotype.Component;

import com.stockmarket.stock.command.model.Stock;
import com.stockmarket.stock.command.model.StockRequest;
import com.stockmarket.stock.command.entity.StockEntity;

@Component
public class CommandDataMapper {

	public StockEntity mapToEntity(StockRequest stockReq, String companyCode) {
		StockEntity entity = new StockEntity();
		entity.setComCode(companyCode);
		entity.setPrice(stockReq.getPrice());
		entity.setTimeStamp(Timestamp.from(Instant.now()));
		entity.setCurrencyCode(stockReq.getCurrencyCode());
		return entity;

	}

	public Stock mapToResponseEntity(StockEntity entity) {
		Stock stock = new Stock();
		stock.setPrice(entity.getPrice());
		stock.setCurrencyCode(entity.getCurrencyCode());
		stock.setTimeStamp(entity.getTimeStamp());
		return stock;
	}

}
