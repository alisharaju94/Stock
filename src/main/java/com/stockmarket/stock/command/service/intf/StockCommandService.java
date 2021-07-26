package com.stockmarket.stock.command.service.intf;

import java.security.NoSuchAlgorithmException;

import com.stockmarket.stock.command.model.Stock;
import com.stockmarket.stock.command.model.StockRequest;

public interface StockCommandService {

	Stock addStock(StockRequest stock, String companyCode) throws NoSuchAlgorithmException;

}
