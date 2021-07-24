package com.stockmarket.stock.entity;

import java.math.BigDecimal;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

import lombok.Data;

@Data
public class StockPrimaryKey {
	@DynamoDBHashKey(attributeName = "com_code")
	private String comCode;

	@DynamoDBRangeKey(attributeName = "time_stamp")
	private long timeStamp;

}
