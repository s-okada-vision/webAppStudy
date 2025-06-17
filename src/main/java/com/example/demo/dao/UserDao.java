package com.example.demo.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.data.UserData;
import com.example.demo.data.ShopData;
@Repository
public class UserDao {

	private final JdbcTemplate jdbcTemplate;

	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	//usersテーブルのデータを取得しUserDataオブジェクトのリストとして返す
	public List<UserData> findAll() {
		String sql = "SELECT * FROM users";
		//jdbcTemplate.query はSQLを実行し、結果を処理するSpringの便利なメソッド
		//ラムダ式 (resultSet, rowNum) で一行ずつオブジェクトへ変換する関数型インターフェース
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			UserData item = new UserData();
			item.setId(rs.getLong("id")); //resultSetからidカラム値をlong型で取得しitemにセット
			item.setName(rs.getString("name"));
			item.setEmail(rs.getString("email"));
			return item; //item値をUserDataに返す
		});
	}

	// 曖昧検索する場合に使うメソッド
	public List<UserData> findUser(String name) {
		//?(プレースホルダー)で値を仮置きしトラブル対策
		String sql = "SELECT * FROM users WHERE name like ?";

		// likeで使いたいので%を追加する（%を入れるとnameを含む全ての文字列を検索可能）
		String searchStr = "%" + name + "%";
		// ラムダ式(rs=ResultSet, rowNum=行番号)でＳＱＬ結果をレスポンス
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			UserData item = new UserData();
			item.setId(rs.getLong("id"));
			item.setName(rs.getString("name"));
			item.setEmail(rs.getString("email"));
			return item;
		}, searchStr); //？に値を返す
	}
	
	public List<ShopData> findAll2() {
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
}
