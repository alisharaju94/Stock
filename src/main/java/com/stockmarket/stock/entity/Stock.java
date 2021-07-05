package com.stockmarket.stock.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "company")
public class Stock {
	@PrimaryKey(value = "code")
	private String companyCode;
	@Column(value = "name")
	private String companyName;
	@Column(value = "ceo")
	private String companyCEO;
	@Column(value = "turn_over")
	private String companyTurnOver;
	@Column(value = "url")
	private String companyUrl;
	@Column(value = "listing")
	private String listing;

	/**
	 * @return the comapnyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param comapnyCode the comapnyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyCEO
	 */
	public String getCompanyCEO() {
		return companyCEO;
	}

	/**
	 * @param companyCEO the companyCEO to set
	 */
	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}

	/**
	 * @return the companyTurnOver
	 */
	public String getCompanyTurnOver() {
		return companyTurnOver;
	}

	/**
	 * @param companyTurnOver the companyTurnOver to set
	 */
	public void setCompanyTurnOver(String companyTurnOver) {
		this.companyTurnOver = companyTurnOver;
	}

	/**
	 * @return the companyUrl
	 */
	public String getCompanyUrl() {
		return companyUrl;
	}

	/**
	 * @param companyUrl the companyUrl to set
	 */
	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	/**
	 * @return the listing
	 */
	public String getListing() {
		return listing;
	}

	/**
	 * @param listing the listing to set
	 */
	public void setListing(String listing) {
		this.listing = listing;
	}

	@Override
	public String toString() {
		return "Company [companyCode=" + companyCode + ", companyName=" + companyName + ", companyCEO=" + companyCEO
				+ ", companyTurnOver=" + companyTurnOver + ", companyUrl=" + companyUrl + ", listing=" + listing + "]";
	}
}
