package com.stockmarket.stock.command.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "stock")
@IdClass(StockPrimaryKey.class)
public class StockEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "price")
	private BigDecimal price;
	
	@Id
	@Column(name = "time_stamp")
	private Timestamp timeStamp;
	
	@Column(name = "com_code")
	private String comCode;

	@Column(name = "currency_code")
	private String currencyCode;

}
