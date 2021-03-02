package com.pm.entity;

import java.util.ArrayList;
import java.util.List;

public class Read {
	private Integer id;
	private String read_writer;//文章作者
	private String read_content;//文章内容
	private String read_image;
	private Integer read_zan;
	private String read_title;
	private String read_summary;
	private Integer read_commentNum;
	private String user_nickname;
	private List<Comment> comments = new ArrayList<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRead_writer() {
		return read_writer;
	}
	public void setRead_writer(String read_writer) {
		this.read_writer = read_writer;
	}
	public String getRead_content() {
		return read_content;
	}
	public void setRead_content(String read_content) {
		this.read_content = read_content;
	}
	public Integer getRead_zan() {
		return read_zan;
	}
	public void setRead_zan(Integer read_zan) {
		this.read_zan = read_zan;
	}
	public Integer getRead_commentNum() {
		return read_commentNum;
	}
	public void setRead_commentNum(Integer read_commentNum) {
		this.read_commentNum = read_commentNum;
	}
	public String getRead_image() {
		return read_image;
	}
	public void setRead_image(String read_image) {
		this.read_image = read_image;
	}
	
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public String getRead_title() {
		return read_title;
	}
	public void setRead_title(String read_title) {
		this.read_title = read_title;
	}
	public String getRead_summary() {
		return read_summary;
	}
	public void setRead_summary(String read_summary) {
		this.read_summary = read_summary;
	}
	@Override
	public String toString() {
		return "Read [id=" + id + ", read_writer=" + read_writer + ", read_content=" + read_content + ", read_image="
				+ read_image + ", read_zan=" + read_zan + ", read_title=" + read_title + ", read_summary="
				+ read_summary + ", read_commentNum=" + read_commentNum + ", user_nickname=" + user_nickname
				+ ", comments=" + comments + "]";
	}
	
	
}
