package com.example.demo.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
@EnableAutoConfiguration
public class TopController {

	// トップ画面
	@GetMapping({ "/" })
	public String top() {
		// templates/index.htmlにリンクされる
		return "index";
	}
}
