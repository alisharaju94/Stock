package com.stockmarket.stock.service.impl;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.stock.entity.StockDetails;
import com.stockmarket.stock.entity.StockEntity;
import com.stockmarket.stock.mapper.DataMapper;
import com.stockmarket.stock.model.StockResponse;
import com.stockmarket.stock.model.Stock;
import com.stockmarket.stock.model.StockRangeQueryParams;
import com.stockmarket.stock.model.StockRequest;
import com.stockmarket.stock.repo.impl.StockRepoCommonImpl;
import com.stockmarket.stock.service.intf.StockService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private DataMapper dataMapper;
	@Autowired
	private StockRepoCommonImpl stockRepoImpl;

	@Override
	public Stock addStock(StockRequest stock, String companyCode) throws NoSuchAlgorithmException {
		StockEntity entity = dataMapper.mapToEntity(stock, companyCode);
		entity = stockRepoImpl.insertStock(entity);
		return dataMapper.mapToResponseEntity(entity);
	}

	@Override
	public StockResponse getStock(StockRangeQueryParams params) {
		StockDetails stockDetails = null;
		if (!ObjectUtils.isEmpty(params.getStart()) || !ObjectUtils.isEmpty(params.getEnd())) {
			stockDetails = stockRepoImpl.getStockForRange(params);
		} else {
			stockDetails = stockRepoImpl.getStocksOfCompany(params.getCompanyCode());
		}
		return dataMapper.mapToResponseEntityList(stockDetails);
	}

	@Override
	public void delete(String companyCode) {
		stockRepoImpl.deleteStocks(companyCode);

	}

}
