package com.stockmarket.stock.service.impl;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.stock.entity.StockDetails;
import com.stockmarket.stock.mapper.DataMapper;
import com.stockmarket.stock.model.StockResponse;
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
	public StockResponse addStock(StockRequest stock, long companyCode) throws NoSuchAlgorithmException {
		StockDetails entityDetails = dataMapper.mapToEntity(stock, companyCode);
		entityDetails = stockRepoImpl.insertStock(entityDetails);
		return dataMapper.mapToResponsBean(entityDetails);
	}


}
