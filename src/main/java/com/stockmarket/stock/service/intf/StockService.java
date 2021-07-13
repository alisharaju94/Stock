package com.stockmarket.stock.service.intf;

import java.security.NoSuchAlgorithmException;

import com.stockmarket.stock.model.StockResponse;
import com.stockmarket.stock.model.Stock;
import com.stockmarket.stock.model.StockRangeQueryParams;
import com.stockmarket.stock.model.StockRequest;

public interface StockService {

	Stock addStock(StockRequest stock, String companyCode) throws NoSuchAlgorithmException;

	StockResponse getStock(StockRangeQueryParams params);

	void delete(String companyCode);

}
