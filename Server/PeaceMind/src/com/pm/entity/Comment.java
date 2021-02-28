package com.pm.entity;

import java.util.ArrayList;
import java.util.List;

public class Comment {
	private Integer id;
    private Integer readId;//文章id
    private Integer commenmtFromId;//评论的用户id
    private Integer commentZanNum;
    private String commentFromNickname;//评论用户昵称
    private String commentFromContent;//评论的内容
    private String commentFromTime;//评论时间
    private List<Reply> replies = new ArrayList<>();
    
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReadId() {
		return readId;
	}
	public void setReadId(Integer readId) {
		this.readId = readId;
	}
	public Integer getCommenmtFromId() {
		return commenmtFromId;
	}
	public void setCommenmtFromId(Integer commenmtFromId) {
		this.commenmtFromId = commenmtFromId;
	}
	public Integer getCommentZanNum() {
		return commentZanNum;
	}
	public void setCommentZanNum(Integer commentZanNum) {
		this.commentZanNum = commentZanNum;
	}
	public String getCommentFromNickname() {
		return commentFromNickname;
	}
	public void setCommentFromNickname(String commentFromNickname) {
		this.commentFromNickname = commentFromNickname;
	}
	public String getCommentFromContent() {
		return commentFromContent;
	}
	public void setCommentFromContent(String commentFromContent) {
		this.commentFromContent = commentFromContent;
	}
	public String getCommentFromTime() {
		return commentFromTime;
	}
	public void setCommentFromTime(String commentFromTime) {
		this.commentFromTime = commentFromTime;
	}
    
    
}
