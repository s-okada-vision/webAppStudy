package com.example.demo.controller;

import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Repository; //DB操作担当
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate; //DB接続補助
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model; // htmlへ値を渡す入れ物

@Controller // Web画面を制御する
public class LoginController {
	
	//DB照合
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	//ログイン画面へアクセス
	@GetMapping( "/login" )
	public String getLogin(Model model) {
		return "login";
	}
	//ログイン失敗
	@GetMapping( "/error" )
	public String getLoginError(Model model) {
		System.out.println("ログインに失敗");
		model.addAttribute("error","IDまたはパスワードが一致しません。");
		return "login";
	}
	//ログイン判定
	@PostMapping( "/login" )
	public String postLogin(@RequestParam String USER_ID,
							@RequestParam String USER_PASSWORD,
							Model model) {
		String sql = "SELECT USER_ID　FROM M_USR WHERE USER_ID = ? AND USER_PASSWORD = ?";
		
		try {
			String findUser = jdbcTemplate.queryForObject(sql, String.class, USER_ID, USER_PASSWORD);
			model.addAttribute(USER_ID, findUser);
			return "login";
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			 model.addAttribute("error", "ユーザー名またはパスワードが間違っています");
		return "products";
	}
	}
}

