package com.stockmarket.stock.query.entity;

import java.math.BigDecimal;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Table(value = "stock")
@Data
public class StockEntity {

	@PrimaryKey
	private StockPrimaryKey primaryKey;
	
	@Column(value = "price")
	private BigDecimal price;

	@Column(value = "currency_code")
	private String currencyCode;

}
