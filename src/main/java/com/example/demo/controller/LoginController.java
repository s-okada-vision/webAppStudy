package com.example.demo.controller;


import java.util.List;

//import org.springframework.stereotype.Repository; //DB操作担当
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // htmlへ値を渡す入れ物

import com.example.demo.dao.LoginDao;
import com.example.demo.data.LoginData;

@Controller // Web画面を制御する
public class LoginController {
	
	//DB照合
	@Autowired
	private final LoginDao loginDao;
	
	public LoginController(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	//ログイン画面を表示
	@GetMapping("/login")
	public ModelAndView LoginPage() {
		return new ModelAndView("login");
	}


	//ログイン判定(RequestParam＝URLから値を受け取る)
	@PostMapping("/login")
	public ModelAndView LoginCheck(
			@RequestParam("ID") String id,
			@RequestParam("PASSWORD") String password) {
 
		List<LoginData> result = loginDao.findData(id, password);

        ModelAndView modelAndView = new ModelAndView();
        if (!result.isEmpty()) {
            modelAndView.setViewName("products"); // ログイン成功時の遷移画面
            modelAndView.addObject("items", result);
        } else {
            modelAndView.setViewName("login"); // ログイン失敗時はログイン画面に戻す
            modelAndView.addObject("error", "IDまたはパスワードが一致しません。");
        }
        return modelAndView;
    }	

	//ログイン失敗
	@GetMapping( "/error" )
	public ModelAndView LoginError(Model model) {
		ModelAndView modelAndView = new ModelAndView("login"); //ログイン画面に戻る
		modelAndView.addObject("error", "ログインに失敗しました。");
		return modelAndView;
	}

}

