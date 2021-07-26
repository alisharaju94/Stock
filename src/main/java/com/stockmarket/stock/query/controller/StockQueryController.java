/**
 * 
 */
package com.stockmarket.stock.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.stock.query.model.StockRangeQueryParams;
import com.stockmarket.stock.query.model.StockResponse;
import com.stockmarket.stock.query.service.intf.StockQueryService;

/**
 * @author ALISH
 *
 */
@RestController
@RequestMapping(value = "/stock")
public class StockQueryController {

	@Autowired
	private StockQueryService stockService;

	@PostMapping
	public StockResponse getStock(@RequestBody StockRangeQueryParams params) {
		return stockService.getStock(params);
	}

}
