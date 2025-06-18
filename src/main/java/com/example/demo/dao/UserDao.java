package com.example.demo.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.data.UserData;
import com.example.demo.form.UserRegistForm;

@Repository
public class UserDao {

	private final JdbcTemplate jdbcTemplate;

	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<UserData> findAll() {
		String sql = "SELECT * FROM users";

		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			UserData item = new UserData();
			item.setId(rs.getLong("id"));
			item.setName(rs.getString("name"));
			item.setEmail(rs.getString("email"));
			return item;
		});
	}

	// 曖昧検索する場合に使うメソッド
	public List<UserData> findUser(String name) {
		String sql = "SELECT * FROM users WHERE name like ?";

		// likeで使いたいので%を追加する
		String searchStr = "%" + name + "%";

		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			UserData item = new UserData();
			item.setId(rs.getLong("id"));
			item.setName(rs.getString("name"));
			item.setEmail(rs.getString("email"));
			return item;
		}, searchStr);
	}

	// 新規登録メソッド
	public void insert(UserRegistForm form) {
		String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
		jdbcTemplate.update(sql, form.getName(), form.getEmail());
		
		// 本当はエラー発生時に例外をキャッチしてエラーページに飛ばすが一旦やらない
	}

}
