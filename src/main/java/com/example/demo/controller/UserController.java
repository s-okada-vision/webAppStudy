package com.example.demo.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.UserDao;
import com.example.demo.data.UserData;
import com.example.demo.form.UserRegistForm;

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

	// ユーザー登録画面
	@GetMapping({ "/user/registForm" })
	public ModelAndView registForm(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userRegistForm");
		return modelAndView;
	}

	// ユーザー登録
	@PostMapping({ "/user/regist" })
	public ModelAndView regist(@ModelAttribute UserRegistForm form, Model model) {
		// @ModelAttributeを使うと、フォームの名前とクラスのメンバ変数名を自動的にマッピングしてくれる
		
		// データ登録（本当は入力チェックとかが必要だが、今はやらない）
		userDao.insert(form);

		ModelAndView modelAndView = new ModelAndView();
		// 登録が成功したので一覧画面にリダイレクトする
		modelAndView.setViewName("redirect:/user");
		return modelAndView;
	}
}
