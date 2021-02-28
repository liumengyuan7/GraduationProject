package com.pm.entity;

import java.util.Date;

public class Reply {
	private Integer id;
	private Integer comment_id;//评论id
	private Integer from_id;//回复的用户id
	private Integer to_id;
	private String reply_content;
	private String reply_time;
	private String from_user_nickname;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	public Integer getFrom_id() {
		return from_id;
	}
	public void setFrom_id(Integer from_id) {
		this.from_id = from_id;
	}
	public Integer getTo_id() {
		return to_id;
	}
	public void setTo_id(Integer to_id) {
		this.to_id = to_id;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_time() {
		return reply_time;
	}
	public void setReply_time(String reply_time) {
		this.reply_time = reply_time;
	}
	public String getFrom_user_nickname() {
		return from_user_nickname;
	}
	public void setFrom_user_nickname(String from_user_nickname) {
		this.from_user_nickname = from_user_nickname;
	}
	
	
	
}
