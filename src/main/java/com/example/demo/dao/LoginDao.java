package com.example.demo.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.data.LoginData;
//import com.example.demo.form.LoginEditForm;

@Repository
public class LoginDao {

	private final JdbcTemplate jdbcTemplate;

	public LoginDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//ログイン時、DBからID、パスワードの一致検索
	public List<LoginData> findData(String user_Id, String user_Password) {
		String sql = "SELECT * FROM M_USR WHERE USER_ID = ? AND USER_PASSWORD = ?";
		
		return jdbcTemplate.query(sql, (rs, rowNum) -> { //ラムダ式(引数) -> 処理
			//ユーザー情報格納クラス、DBのカラム値をフィールドにセット
			LoginData item = new LoginData();
			item.setId(rs.getString("USER_ID"));
			item.setPassword(rs.getString("USER_PASSWORD"));
			return item;
		},  user_Id, user_Password); //値を返す
	}
	
	//編集詳細取得
	public LoginData findId(String user_Id) {
		String sql = "SELECT * FROM M_USR WHERE USER_ID = ?";
		return jdbcTemplate.queryForObject(sql,(rs, rowNum) -> {
			LoginData item = new LoginData();
			item.setId(rs.getString("USER_ID"));
			item.setPassword(rs.getString("USER_PASSWORD"));
			return item;
		},  user_Id);
	}
	
	//パスワード編集登録
	public void updatePassword(String userId, String newPassword) {
	String sql = "UPDATE M_USR SET USER_PASSWORD = ? WHERE USER_ID = ?";
		jdbcTemplate.update(sql, newPassword, userId);//値を確認してからなおす
	}
	
	// DB接続確認（ユーザー数を集計し取得）
	public int dbCheck() {
	    String sql = "SELECT COUNT(*) FROM M_USR";
	    return jdbcTemplate.queryForObject(sql, Integer.class); //sqlの引数をintegerで受け取る
	}


}
