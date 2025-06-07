package com.example.demo.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.UserDao;
import com.example.demo.data.UserData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@EnableAutoConfiguration
public class UserController {

	// DBアクセスオブジェクトの生成（おまじない）
	private final UserDao userDao;

	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}

	// ユーザー一覧画面
	@GetMapping({ "/user" })
	public ModelAndView top(Model model) {
		// DBからデータ取得
		List<UserData> items = userDao.findAll();

		// Nakaがつく人を探す場合はこうする
		// List<UserData> items = userDao.findUser("Naka");

		// templates/user.htmlにリンクされる
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");

		// HTML側へデータをリンク
		modelAndView.addObject("items", items);
		return modelAndView;
	}
}
