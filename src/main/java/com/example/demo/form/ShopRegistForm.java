package com.example.demo.form;

import java.math.BigDecimal;
//情報をデータベースに登録するメソッド
public class ShopRegistForm {

	private String barcode;
	private String name;
	private BigDecimal costPrice;
	private BigDecimal salePrice;
	
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
	
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	
	public void setCostPrice(BigDecimal cost_price) {
		this.costPrice = cost_price;
	}
	
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	
	public void setSalePrice(BigDecimal sale_price) {
		this.salePrice = sale_price;
	}
}
