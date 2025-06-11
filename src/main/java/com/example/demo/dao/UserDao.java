package com.example.demo.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.data.UserData;

@Repository
public class UserDao {

	private final JdbcTemplate jdbcTemplate;

	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	//usersテーブルのデータを取得しUserDataオブジェクトのリストとして返す
	public List<UserData> findAll() {
		String sql = "SELECT * FROM users";
		//SQLを実行して結果を処理する便利なメソッド
		//第2引数のラムダ式 (rs, rowNum) で一行ずつオブジェクトへ変換
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			UserData item = new UserData();
			item.setId(rs.getLong("id")); 
			item.setName(rs.getString("name"));
			item.setEmail(rs.getString("email"));
			return item; //カラム値を取得しUserDataにセット
		});
	}

	// 曖昧検索する場合に使うメソッド
	public List<UserData> findUser(String name) {
		//?(プレースホルダー)で値を仮置きしトラブル対策
		String sql = "SELECT * FROM users WHERE name like ?";

		// likeで使いたいので%を追加する（%を入れるとnameを含む全ての文字列を検索可能）
		String searchStr = "%" + name + "%";

		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			UserData item = new UserData();
			item.setId(rs.getLong("id"));
			item.setName(rs.getString("name"));
			item.setEmail(rs.getString("email"));
			return item;
		}, searchStr); //？に値を返す
	}
}
