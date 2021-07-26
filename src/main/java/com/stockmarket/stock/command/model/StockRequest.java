package com.stockmarket.stock.command.model;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Stock details")
@Data
public class StockRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "Price of a unit", example = "30.15")
	private BigDecimal price;

	@ApiModelProperty(notes = "Currency code. This should be a 3 letter code.", example = "INR")
	private String currencyCode;

}
