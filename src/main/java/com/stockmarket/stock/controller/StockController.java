/**
 * 
 */
package com.stockmarket.stock.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.stock.model.StockResponse;
import com.stockmarket.stock.model.StockRequest;
import com.stockmarket.stock.service.intf.StockService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author ALISH
 *
 */
@RestController
@RequestMapping(value = "/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	@PostMapping(value = "/add/{companyCode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add Stock details", notes = "Endpoint to add stock details of a company", response = StockResponse.class)
	public StockResponse addStock(
			@ApiParam(value = "companyCode", required = true, example = "123") @PathVariable("companyCode") long companyCode,
			@ApiParam(value = "stock", required = true) @RequestBody StockRequest stock)
			throws NoSuchAlgorithmException {
		return stockService.addStock(stock, companyCode);
	}
}
