package com.stockmarket.stock.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class Stock implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal price;
	private String currencyCode;
	private long timeStamp;
}
