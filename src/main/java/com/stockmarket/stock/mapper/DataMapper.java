package com.stockmarket.stock.mapper;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.stockmarket.stock.contants.CommonConstants;
import com.stockmarket.stock.entity.StockDetails;
import com.stockmarket.stock.entity.StockEntity;
import com.stockmarket.stock.model.Stock;
import com.stockmarket.stock.model.StockDetail;
import com.stockmarket.stock.model.StockRequest;
import com.stockmarket.stock.model.StockResponse;

@Component
public class DataMapper {

	public StockDetails mapToEntity(StockRequest stockReq, long companyCode) {
		StockDetails stockDetails = new StockDetails();
		List<StockEntity> stocks = Lists.newArrayList();
		stockReq.getStocks().forEach(item -> {
			StockEntity entity = new StockEntity();
			entity.setCompanyCode(companyCode);
			entity.setName(item.getName().toUpperCase());
			entity.setPrice(item.getPrice());
			entity.setCurrencyCode(item.getCurrencyCode());
			setStockCodeAndTimeStampForEach(item, entity);
			stocks.add(entity);
		});
		stockDetails.setStocks(stocks);
		return stockDetails;

	}

	public StockResponse mapToResponsBean(StockDetails entityDetails) {
		StockResponse res = new StockResponse();
		List<Stock> stocks = Lists.newArrayList();
		entityDetails.getStocks().forEach(entity -> {
			Stock stock = new Stock();
			stock.setCompanyCode(entity.getCompanyCode());
			stock.setStockCode(entity.getStockCode());
			stock.setName(entity.getName());
			stock.setPrice(entity.getPrice());
			stock.setCurrencyCode(entity.getCurrencyCode());
			stock.setTimeStamp(entity.getTimeStamp());
			stocks.add(stock);
		});
		return res;
	}

	private void setStockCodeAndTimeStampForEach(StockDetail item, StockEntity entity) {
		entity.setTimeStamp(Timestamp.from(Instant.now()));
		String stockCode = item.getCode();
		if (stockCode == null) {
			try {
				stockCode = CommonConstants.getRandomID(entity.getName().hashCode());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				throw new IllegalStateException();
			}
		}
		entity.setStockCode(stockCode);
	}
}
