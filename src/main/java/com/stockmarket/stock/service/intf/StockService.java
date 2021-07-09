package com.stockmarket.stock.service.intf;

import java.security.NoSuchAlgorithmException;

import com.stockmarket.stock.model.StockResponse;
import com.stockmarket.stock.model.StockRequest;

public interface StockService {

	StockResponse addStock(StockRequest stock, long companyCode) throws NoSuchAlgorithmException;

}
