package com.stockmarket.stock.model;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Stock details")
public class StockRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(name = "stocks")
	private List<StockDetail> stocks;

	/**
	 * @return the stocks
	 */
	public List<StockDetail> getStocks() {
		return stocks;
	}

	/**
	 * @param stocks the stocks to set
	 */
	public void setStocks(List<StockDetail> stocks) {
		this.stocks = stocks;
	}

}
