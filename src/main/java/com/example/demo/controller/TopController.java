package com.example.demo.controller;
//Spring Boot の 自動設定機能を有効にする
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//HTTPの GETリクエスト（例：ブラウザでURLにアクセス）に対応するメソッドを作るためのアノテーション
import org.springframework.web.bind.annotation.GetMapping;
//HTMLなどのテンプレートをWeb画面に返すときに使うコントローラー
import org.springframework.stereotype.Controller;

@Controller //画面を返すコントローラー
@EnableAutoConfiguration //自動設定有効
public class TopController {

	// トップ画面
	@GetMapping({ "/" }) //"/"にアクセスがあればメソッド呼び出す
	public String top() {
		// templates/index.htmlにリンクされる
		return "index";
	}
}
