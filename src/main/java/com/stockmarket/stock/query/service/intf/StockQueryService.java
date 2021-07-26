package com.stockmarket.stock.query.service.intf;

import com.stockmarket.stock.query.model.StockRangeQueryParams;
import com.stockmarket.stock.query.model.StockResponse;

public interface StockQueryService {

	StockResponse getStock(StockRangeQueryParams params);

}
