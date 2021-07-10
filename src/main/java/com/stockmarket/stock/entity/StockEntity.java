package com.stockmarket.stock.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Table(value = "stock")
@Data
public class StockEntity {
	@PrimaryKey
	@Column(value = "sequence")
	private long sequence;

	@Column(value = "company_code")
	private long companyCode;

	@Column(value = "stock_code")
	private String stockCode;

	@Column(value = "name")
	private String name;

	@Column(value = "price")
	private BigDecimal price;

	@Column(value = "currency_code")
	private String currencyCode;

	@Column(value = "time_stamp")
	private Timestamp timeStamp;

}
