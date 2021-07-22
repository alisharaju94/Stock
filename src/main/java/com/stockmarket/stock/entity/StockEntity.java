package com.stockmarket.stock.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;


@Data
@DynamoDBTable(tableName = "stock")
public class StockEntity {


	@DynamoDBHashKey(attributeName = "price")
	private BigDecimal price;

	@DynamoDBAttribute(attributeName = "time_stamp")
	private Timestamp timeStamp;

	
	@DynamoDBAttribute(attributeName = "comCode")
	private String comCode;
	private String currencyCode;

}