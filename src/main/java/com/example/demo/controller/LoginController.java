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
	public ModelAndView LoginCheck(@RequestParam("ID") String id, @RequestParam("PASSWORD") String password) {

		List<LoginData> result = loginDao.findData(id, password);

		ModelAndView modelAndView = new ModelAndView();
		if (!result.isEmpty()) {
			modelAndView.setViewName("redirect:/products"); // ログイン成功時の遷移画面
			// redirect:/を入れる事で遷移先のコントローラが呼ばれている。
		} else {
			modelAndView.setViewName("login"); // ログイン失敗時はログイン画面に戻す
			modelAndView.addObject("error", "IDまたはパスワードが一致しません。");
		}
		return modelAndView;
	}

	// パスワード編集画面追加
	
	@GetMapping({"/login/LoginPasswordEdit"}) 
	public ModelAndView Form(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("LoginPasswordEdit");
		modelAndView.addObject("loginEditForm", new LoginEditForm());
		modelAndView.addObject("step", 1); //現在のパスワード
		return modelAndView;
	}
	
	@PostMapping("/login/LoginPasswordEdit/verify")
	public ModelAndView verifyPass(@ModelAttribute LoginEditForm form, HttpSession session) {
		LoginData user = loginDao.findId(form.getId());
		ModelAndView modelAndView = new ModelAndView("LoginPasswordEdit");
		
		//パスワードが値があり、かつ一致しているとき
		if(user != null && user.getPassword().equals(form.getPassword())){
			session.setAttribute("loginUserId", form.getId());
			form.setPassword("");//現在のパスワードをクリア
			modelAndView.addObject("step", 2); //ステップ２＝新しいパスワード
		} else {
			modelAndView.addObject("error","IDまたはパスワードが間違っています");
			modelAndView.addObject("step", 1);
		}
		modelAndView.addObject("loginEditForm", form);
		return modelAndView;
	}

	// 更新登録
	@PostMapping({ "/login/LoginPasswordAEdit/update" })
	public ModelAndView updatePassword(@ModelAttribute LoginEditForm form, HttpSession session) {
		String userId = (String) session.getAttribute("loginUserId");
		
		//IDに値がない場合
		if(userId == null) {
			return new ModelAndView("redirect:/login");
		}
		loginDao.updatePassword(userId, form.getNew_Password());
		session.removeAttribute("loginUserId");
		return new ModelAndView("redirect:/login");
	}
}
