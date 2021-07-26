package com.stockmarket.stock.query.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.stockmarket.stock.query.entity.StockDetails;
import com.stockmarket.stock.query.mapper.QueryDataMapper;
import com.stockmarket.stock.query.model.StockRangeQueryParams;
import com.stockmarket.stock.query.model.StockResponse;
import com.stockmarket.stock.query.repo.impl.StockQueryRepoImpl;
import com.stockmarket.stock.query.service.intf.StockQueryService;

@Service
public class StockQueryServiceImpl implements StockQueryService {

	@Autowired
	private QueryDataMapper dataMapper;
	@Autowired
	private StockQueryRepoImpl stockRepoImpl;

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


}
