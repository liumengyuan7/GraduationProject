package com.pm.entity;

public class Read {
	private Integer id;
	private String read_writer;//文章作者
	private String read_content;//文章内容
	private Integer read_zan;
	private Integer read_commentNum;
	
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
	
	
}
