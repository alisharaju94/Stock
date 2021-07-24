package com.stockmarket.stock.mapper;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.stockmarket.stock.entity.StockDetails;
import com.stockmarket.stock.entity.StockEntity;
import com.stockmarket.stock.model.Stock;
import com.stockmarket.stock.model.StockRequest;
import com.stockmarket.stock.model.StockResponse;

@Component
public class DataMapper {

	public StockEntity mapToEntity(StockRequest stockReq, String companyCode) {
		StockEntity entity = new StockEntity();
		entity.setComCode(companyCode);
		entity.setPrice(stockReq.getPrice());
		entity.setTimeStamp(Instant.now().toEpochMilli());
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

	public StockResponse mapToResponseEntityList(StockDetails stockDetails) {
		StockResponse stockResponse = new StockResponse();
		List<Stock> stocksList = Lists.newArrayList();
		stockDetails.getStocks().forEach(entity -> {
			Stock stock = mapToResponseEntity(entity);
			stocksList.add(stock);
		});
		stockResponse.setStockDetails(stocksList);
		return stockResponse;
	}

}
