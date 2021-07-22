package com.stockmarket.stock.service.intf;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

import com.stockmarket.stock.entity.StockEntity;
import com.stockmarket.stock.model.StockResponse;
import com.stockmarket.stock.model.Stock;
import com.stockmarket.stock.model.StockRangeQueryParams;
import com.stockmarket.stock.model.StockRequest;

public interface StockService {
	void saveStockData(StockEntity stockEntity);

	Stock addStock(StockRequest stock, String companyCode) throws NoSuchAlgorithmException;


}
