package com.pm.entity;

public class Joke {
	private Integer id;
	private Integer laugh_id;
	private String laugh_content;
	private Integer laugh_zan;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLaugh_id() {
		return laugh_id;
	}
	public void setLaugh_id(Integer laugh_id) {
		this.laugh_id = laugh_id;
	}
	public String getLaugh_content() {
		return laugh_content;
	}
	public void setLaugh_content(String laugh_content) {
		this.laugh_content = laugh_content;
	}
	public Integer getLaugh_zan() {
		return laugh_zan;
	}
	public void setLaugh_zan(Integer laugh_zan) {
		this.laugh_zan = laugh_zan;
	}
	@Override
	public String toString() {
		return "Joke [id=" + id + ", laugh_id=" + laugh_id + ", laugh_content=" + laugh_content + ", laugh_zan="
				+ laugh_zan + "]";
	}


	
}
