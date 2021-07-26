package com.stockmarket.stock.command.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class StockPrimaryKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigDecimal price;

	private Timestamp timeStamp;
}
