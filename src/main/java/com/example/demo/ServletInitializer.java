package com.example.demo;
//カスタマイズして起動するクラスを読み込み
import org.springframework.boot.builder.SpringApplicationBuilder;
//WARファイルとして外部サーバーにデプロイするためのベースクラスを読み込み
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
	//元のクラスを上書き
	@Override
	//アプリの起動設定をカスタマイズするためのメソッド
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class); //起動対象のクラスを（）内で指定し戻す
	}
}
