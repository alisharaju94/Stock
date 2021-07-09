package com.stockmarket.stock.model;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class StockDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Stock code", example = "S_000000")
	private String code;

	@ApiModelProperty(notes = "Stock name", example = "XYZ1 stock")
	private String name;

	@ApiModelProperty(notes = "Price of a unit", example = "30.15")
	private BigDecimal price;

	@ApiModelProperty(notes = "Currency code. This should be a 3 letter code.", example = "INR")
	private String currencyCode;
}
