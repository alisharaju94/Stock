package com.stockmarket.stock.query.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Table(value = "stock")
@Data
public class StockEntity {

	@PrimaryKey
	private StockPrimaryKey primaryKey;
	
	@Column(value = "com_code")
	private String comCode;

	@Column(value = "currency_code")
	private String currencyCode;

}
