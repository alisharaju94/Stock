package com.stockmarket.stock.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "stock")
public class StockEntity {

	@Id
	private StockPrimaryKey primaryKey;

	@DynamoDBAttribute(attributeName = "price")
	private BigDecimal price;

	@DynamoDBAttribute(attributeName = "currency_code")
	private String currencyCode;

	@DynamoDBHashKey(attributeName = "com_code")
	public String getComCode() {
		return this.primaryKey != null ? this.primaryKey.getComCode() : null;
	}

	public void setComCode(String comCode) {
		if (this.primaryKey == null) {
			this.primaryKey = new StockPrimaryKey();
		}
		this.primaryKey.setComCode(comCode);
	}

	@DynamoDBRangeKey(attributeName = "time_stamp")
	public long getTimeStamp() {
		return this.primaryKey != null ? this.primaryKey.getTimeStamp() : null;
	}

	public void setTimeStamp(long timeStamp) {
		if (this.primaryKey == null) {
			this.primaryKey = new StockPrimaryKey();
		}
		this.primaryKey.setTimeStamp(timeStamp);
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}