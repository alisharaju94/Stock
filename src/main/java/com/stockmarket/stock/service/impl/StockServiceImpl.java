package com.stockmarket.stock.service.impl;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import com.stockmarket.stock.repo.intf.StockDataRepository;
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

import javax.inject.Inject;

@Service
public class StockServiceImpl implements StockService {

	private final StockDataRepository stockDataRepository;
	@Inject
	public StockServiceImpl(StockDataRepository stockDataRepository){
		this.stockDataRepository=stockDataRepository;
	}

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
	public void saveStockData(StockEntity stockEntity){
		try {
			stockDataRepository.save(stockEntity);
		} catch(IllegalArgumentException illegalArgumentException){
			throw new IllegalArgumentException(illegalArgumentException.getMessage());
		}

	}

}
