package com.stockmarket.stock.model;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Stock details")
@Data
public class StockRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name = "stocks")
	private List<StockDetail> stocks;

}
