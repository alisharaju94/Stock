package com.stockmarket.stock.command.service.impl;

import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.stock.command.entity.StockEntity;
import com.stockmarket.stock.command.mapper.CommandDataMapper;
import com.stockmarket.stock.command.service.intf.StockCommandService;
import com.stockmarket.stock.command.model.Stock;
import com.stockmarket.stock.command.model.StockRequest;
import com.stockmarket.stock.command.repo.impl.StockCommandRepoImpl;

@Service
public class StockCommandServiceImpl implements StockCommandService {

	@Autowired
	private CommandDataMapper dataMapper;
	@Autowired
	private StockCommandRepoImpl stockRepoImpl;

	@Override
	public Stock addStock(StockRequest stock, String companyCode) throws NoSuchAlgorithmException {
		StockEntity entity = dataMapper.mapToEntity(stock, companyCode);
		entity = stockRepoImpl.insertStock(entity);
		return dataMapper.mapToResponseEntity(entity);
	}


	@Override
	public void delete(String companyCode) {
//		stockRepoImpl.deleteStocks(companyCode);

	}

}
