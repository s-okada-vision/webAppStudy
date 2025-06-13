package com.example.demo.controller;
//Java の リスト型（順序付きのコレクション） を使う
import java.util.List;
/* Spring Boot のWeb機能を使うためのインポート
（ルーティング、画面表示、テンプレート連携など*/
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
//自作のデータアクセスとデータクラスを使う
import com.example.demo.dao.UserDao;
import com.example.demo.data.UserData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

//Web画面を返すコントローラー、自動設定有効
@Controller
@EnableAutoConfiguration
public class UserController {

	// DBアクセスオブジェクトの生成（おまじない）
	// データアクセスオブジェクト（DAO）で、ユーザー情報から取得・保存する役割
	private final UserDao userDao;
	// Spring が UserDao を自動で渡してくれる（コンストラクタインジェクション）
	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}

	// ユーザー一覧画面
	@GetMapping({ "/user" }) //アクセスあれば呼び出す
	public ModelAndView top(Model model) {
		// DBからデータ取得
		 List<UserData> items = userDao.findAll();

		 // Nakaがつく人を探す場合はこうする（確認済）
		// List<UserData> items = userDao.findUser("Naka");

		// templates/user.htmlにリンクされる
		// どの画面（View）を表示するか、どんなデータを渡すか、をまとめて返すクラス
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");

		// HTML側へデータをリンク
		modelAndView.addObject("items", items);
		return modelAndView;
	}
/*	// ドラッグストア商品一覧　shop作成中
	@GetMapping({ "/shop" }) //アクセスあれば呼び出す
	public ModelAndView top2(Model mode2) {
		// DBからデータ取得
		 List<ShopData> items = userDao.findAll();

		 // Nakaがつく人を探す場合はこうする（確認済）
		// List<UserData> items = userDao.findUser("Naka");

		// templates/user.htmlにリンクされる
		// どの画面（View）を表示するか、どんなデータを渡すか、をまとめて返すクラス
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("shop");

		// HTML側へデータをリンク
		modelAndView.addObject("items", items);
		return modelAndView;
	} */
}
