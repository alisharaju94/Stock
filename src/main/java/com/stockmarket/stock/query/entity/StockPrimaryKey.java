package com.stockmarket.stock.query.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import lombok.Data;

@Data
@PrimaryKeyClass
public class StockPrimaryKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(name = "com_code", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String comCode;

	@PrimaryKeyColumn(name = "time_stamp", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	private Timestamp timeStamp;
}
