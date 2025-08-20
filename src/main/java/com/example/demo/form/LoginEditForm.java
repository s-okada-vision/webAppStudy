package com.example.demo.form;

public class LoginEditForm {

	private String User_Id;
	private String User_Password;// 現在のパスワード
	private String New_Password; // 新しいパスワード
	
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
	
	public String getNew_Password() {
		return New_Password;
	}
	
	public void setNew_Password(String New_Password) {
		this.New_Password = New_Password;
	}
}