package com.pm.entity;

import java.util.Date;

public class User {
	private Integer id;
	private String user_phone;
	private String user_password;
	private String user_sex;
	private String user_image;
	private Date user_register_time;
	private String user_nickname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_image() {
		return user_image;
	}
	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}
	public Date getUser_register_time() {
		return user_register_time;
	}
	public void setUser_register_time(Date user_register_time) {
		this.user_register_time = user_register_time;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", user_phone=" + user_phone + ", user_password=" + user_password + ", user_sex="
				+ user_sex + ", user_image=" + user_image + ", user_register_time=" + user_register_time
				+ ", user_nickname=" + user_nickname + "]";
	}
	
	
}
