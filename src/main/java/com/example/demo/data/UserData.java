package com.example.demo.data;
// DBのユーザー情報を保持しコントローラーと受け渡しをする
public class UserData {

	private Long id;
	private String name;
	private String email;
	//ゲッター＝フレームワークが自動的に値を取得
	public Long getId() {
		return id;
	}
	//セッター＝フレームワークが自動的に値を設定
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
