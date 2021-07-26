package com.stockmarket.stock.command.model;

import java.io.Serializable;
import java.util.List;

public class StockResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Stock> stockDetails;

	/**
	 * @return the stockDetails
	 */
	public List<Stock> getStockDetails() {
		return stockDetails;
	}

	/**
	 * @param stockDetails the stockDetails to set
	 */
	public void setStockDetails(List<Stock> stockDetails) {
		this.stockDetails = stockDetails;
	}

}
