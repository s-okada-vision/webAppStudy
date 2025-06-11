package com.example.demo;
/*Spring Bootアプリを起動するためのクラスを使えるようにする準備*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Spring Boot の自動設定やコンポーネントスキャンなどを有効にする
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//アプリケーション起動
		SpringApplication.run(DemoApplication.class, args);
	}

}
