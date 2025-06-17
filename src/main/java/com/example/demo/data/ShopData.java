package com.example.demo.data;

import java.math.BigDecimal;

// DBのユーザー情報を保持しコントローラーと受け渡しをする
public class ShopData {

	private String barcode;
	private String name;
	private BigDecimal cost_price;
	private BigDecimal sale_price;
	private BigDecimal grossProfit; //粗利益計算追加
	//ゲッター＝フレームワークが自動的に値を取得
	public String getBarcode() {
		return barcode;
	}
	//セッター＝フレームワークが自動的に値を設定
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
//　java側でBigDecimalからint型へ変換し金額の小数点以下を切り捨て、カンマ表示をhtmlに渡す機能
	public String getFormatCost_price() {
		return String.format("%,d", cost_price.intValue());
	}
	public String getFormatSale_price() {
		return String.format("%,d", sale_price.intValue());
	}
	public String getFormatGrossProfit() {
		return String.format("%,d", grossProfit.intValue());			
	}
// 粗利益の計算をしてhtmlに渡す機能
	public BigDecimal getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}
}