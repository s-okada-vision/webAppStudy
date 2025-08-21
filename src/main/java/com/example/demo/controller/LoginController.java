package com.example.demo.controller;

import java.util.List;

//import org.springframework.stereotype.Repository; //DB操作担当
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model; // htmlへ値を渡す入れ物

import com.example.demo.dao.LoginDao;
import com.example.demo.data.LoginData;
import com.example.demo.form.LoginEditForm;
import jakarta.servlet.http.HttpSession;

@Controller // Web画面を制御する
public class LoginController {

	private final LoginDao loginDao;

	public LoginController(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	// ログイン画面を表示
	@GetMapping("/login")
	public ModelAndView LoginPage() {
		return new ModelAndView("login");
	}

	// ログイン判定(RequestParam＝URLから値を受け取る)
	@PostMapping("/login")
	public ModelAndView LoginCheck(@RequestParam("ID") String id, @RequestParam("PASSWORD") String password, HttpSession session) {

		List<LoginData> result = loginDao.findData(id, password);

		ModelAndView modelAndView = new ModelAndView();
		if (!result.isEmpty()) {
			session.setAttribute("loginUserId", id);
			modelAndView.setViewName("redirect:/products"); // ログイン成功時の遷移画面
			// redirect:/を入れる事で遷移先のコントローラが呼ばれている。
		} else {
			modelAndView.setViewName("login"); // ログイン失敗時はログイン画面に戻す
			modelAndView.addObject("error", "IDまたはパスワードが一致しません。");
		}
		return modelAndView;
	}

	// パスワード編集画面追加
	@GetMapping({"/loginEditForm"})
	public ModelAndView LoginEditForm(HttpSession session) {
		String userId = (String) session.getAttribute("loginUserId");
		//IDに値が無い場合
		if(userId == null ) {
			return new ModelAndView("redirect:/login");
		}
		ModelAndView modelAndView = new ModelAndView("loginEditForm");
		modelAndView.addObject("loginId", userId);
		return modelAndView;
	}

	// 更新登録
	@PostMapping({"/loginEditForm"})
	public ModelAndView updatePassword(@ModelAttribute LoginEditForm form, HttpSession session) {
		String userId = (String) session.getAttribute("loginUserId");
		
		//IDに値がない場合
		if(userId == null) {
			return new ModelAndView("redirect:/login");
		}
		//現在のパスワードをDBと比較
		LoginData userData = loginDao.findId(userId);
		if(!form.getPassword().equals(userData.getPassword())){
			ModelAndView errorView = new ModelAndView("loginEditForm");
			errorView.addObject("error", "現在のパスワードが正しくありません");
			errorView.addObject("loginId", userId);
			return errorView;
		}
		//新しいパスワードの値がないとき、空文字のとき
		if (form.getNewPassword() == null || form.getNewPassword().isEmpty()) {
		    ModelAndView errorView = new ModelAndView("loginEditForm");
		    errorView.addObject("error", "新しいパスワードが未入力です");
		    errorView.addObject("loginId", userId);
		    return errorView;
		}

		//パスワード更新
		loginDao.updatePassword(userId, form.getNewPassword());
		session.removeAttribute("loginUserId");
		return new ModelAndView("redirect:/login");
	}
}
