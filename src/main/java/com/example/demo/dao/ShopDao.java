package com.example.demo.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.data.ShopData;
import com.example.demo.form.ShopRegistForm;

@Repository
public class ShopDao {

	private final JdbcTemplate jdbcTemplate;

	public ShopDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<ShopData> findAll() {
		String sql = "SELECT * FROM webdbproducts";
		//jdbcTemplate.query はSQLを実行、結果を処理するSpringの便利なメソッド
		//ラムダ式 (resultSet, rowNum) で一行ずつオブジェクトへ変換する関数型インターフェース
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			ShopData item = new ShopData();
			item.setBarcode(rs.getString("barcode")); //resultSetからidカラム値をlong型で取得しitemにセット
			item.setName(rs.getString("name"));
			item.setCost_price(rs.getBigDecimal("cost_price"));
			item.setSale_price(rs.getBigDecimal("sale_price"));
			return item; //item値を返す
		});
	}
	
	public List<ShopData> findProducts(String name) {
		//?(プレースホルダー)で値を仮置きしトラブル対策
		String sql = "SELECT * FROM webdbproducts WHERE name like ?";

		// likeで使いたいので%を追加する（%を入れるとnameを含む全ての文字列を検索可能）
		String searchStr = "%" + name + "%";
		// ラムダ式(rs=ResultSet, rowNum=行番号)でＳＱＬ結果をレスポンス
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			ShopData item = new ShopData();
			item.setBarcode(rs.getString("barcode")); //resultSetからidカラム値をlong型で取得しitemにセット
			item.setName(rs.getString("name"));
			item.setCost_price(rs.getBigDecimal("cost_price"));
			item.setSale_price(rs.getBigDecimal("sale_price"));
			return item; //item値を返す
		}, searchStr); //？に値を返す
	}

	// 新規登録メソッド
	public void insert(ShopRegistForm form) {
		String sql = "INSERT INTO shop (barcode, name, cost_price, sale_price) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, form.getBarcode(), form.getName(), form.getCost_price(), form.getSale_price());
		
		// 本当はエラー発生時に例外をキャッチしてエラーページに飛ばすが一旦やらない
	}

}
