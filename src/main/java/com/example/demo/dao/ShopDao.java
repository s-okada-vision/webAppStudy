package com.example.demo.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.data.ShopData;
import com.example.demo.form.ShopEditForm;
import com.example.demo.form.ShopRegistForm;

@Repository //DB接続を自動管理してくれる
public class ShopDao {

	private final JdbcTemplate jdbcTemplate;

	public ShopDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	//全件取得
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
	//入力値と一致するものを検索
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
	public void insert(ShopRegistForm form) { //insert（）関数で~formで入力された値を追加する
		String sql = "INSERT INTO webdbproducts (barcode, name, cost_price, sale_price) VALUES (?, ?, ?, ?)"; //?で値を仮置き
		jdbcTemplate.update(sql, form.getBarcode(), form.getName(), form.getCostPrice(), form.getSalePrice());
		//updateで更新するSQL、結果を処理するjdbcTemplate
		
		// 本当はエラー発生時に例外をキャッチしてエラーページに飛ばすが一旦やらない
	}
	// 商品編集のための詳細取得
	public ShopData findBarcode(String barcode) {
		String sql = "SELECT * FROM webdbProducts WHERE barcode = ?";
		return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> { // queryForObject=select文で1件のみ取得
			ShopData item = new ShopData();
			item.setBarcode(rs.getString("barcode")); //resultSetからidカラム値をlong型で取得しitemにセット
			item.setName(rs.getString("name"));
			item.setCost_price(rs.getBigDecimal("cost_price"));
			item.setSale_price(rs.getBigDecimal("sale_price"));
			return item; //item値を返す )
		}, barcode);
	}
	// 編集登録
	public void update(ShopEditForm form) { //insert（）関数で~formで入力された値を追加する
		String sql = "UPDATE webdbproducts SET barcode = ?, name = ?, cost_price = ?, sale_price = ? WHERE barcode = ?"; //?で値を仮置き
		jdbcTemplate.update(sql, form.getBarcode(), form.getName(), form.getCostPrice(), form.getSalePrice(), form.getBarcode());
		//DBを処理するjdbcTemplate、updateメソッドは追加、更新、削除が可能
	}
	// 削除機能
	public void delete(String barcode) { //指定したbarcodeのレコードを削除する
		String sql = "DELETE FROM webdbproducts WHERE barcode = ?";
		jdbcTemplate.update(sql, barcode);
	}
}
