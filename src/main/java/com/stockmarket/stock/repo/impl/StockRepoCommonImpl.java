package com.stockmarket.stock.repo.impl;

import com.stockmarket.stock.repo.intf.StockDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.stockmarket.stock.entity.StockEntity;


@Component
public class StockRepoCommonImpl {

	@Autowired
	private StockDataRepository stockCommonOpRepo;

//	@Autowired

	public StockEntity insertStock(StockEntity entity) {
		entity = stockCommonOpRepo.save(entity);
		return entity;

	}

}
