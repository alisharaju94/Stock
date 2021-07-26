package com.stockmarket.stock.query.mapper;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.stockmarket.stock.query.entity.StockDetails;
import com.stockmarket.stock.query.entity.StockEntity;
import com.stockmarket.stock.query.entity.StockPrimaryKey;
import com.stockmarket.stock.query.model.Stock;
import com.stockmarket.stock.query.model.StockResponse;

@Component
public class QueryDataMapper {

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

	public Stock mapToResponseEntity(StockEntity entity) {
		Stock stock = new Stock();
		stock.setPrice(entity.getPrimaryKey().getPrice());
		stock.setCurrencyCode(entity.getCurrencyCode());
		stock.setTimeStamp(entity.getPrimaryKey().getTimeStamp());
		return stock;
	}

}
