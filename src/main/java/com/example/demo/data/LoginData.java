package com.example.demo.data;

//画面入力されたIDとパスワードを保持する
public class LoginData {

	private String User_Id;
	private String User_Password;
	
	public String getId() {
		return User_Id;		
	}
	
	public void setId(String user_Id) {
		this.User_Id = user_Id;
	}
	
	public String getPassword() {
		return User_Password;
	}
	
	public void setPassword(String user_Password) {
		this.User_Password = user_Password;
	}
}
