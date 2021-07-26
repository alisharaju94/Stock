/**
 * 
 */
package com.stockmarket.stock.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.stock.query.model.Stock;
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
	
	@GetMapping(value = "/latest/{companyCode}")
	public Stock getStock(@PathVariable String companyCode) {
		return stockService.getLatestStock(companyCode);
	}
	
	@DeleteMapping(value ="/{companyCode}")
	public ResponseEntity deleteCompany(@PathVariable String companyCode) {
		 stockService.delete(companyCode);
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
