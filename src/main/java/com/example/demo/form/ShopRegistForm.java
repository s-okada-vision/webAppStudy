package com.example.demo.form;

import java.math.BigDecimal;

public class ShopRegistForm {

	private String barcode;
	private String name;
	private BigDecimal cost_price;
	private BigDecimal sale_price;
	
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getCost_price() {
		return cost_price;
	}
	
	public void setCost_price(BigDecimal cost_price) {
		this.cost_price = cost_price;
	}
	
	public BigDecimal getSale_price() {
		return sale_price;
	}
	
	public void setSale_price(BigDecimal sale_price) {
		this.sale_price = sale_price;
	}
}
