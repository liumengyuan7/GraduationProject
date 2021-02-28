package com.pm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pm.entity.Comment;

public interface CommentMapper {
	public int insertComment(@Param("readId") Integer readId, @Param("userId") Integer userId, 
			@Param("content") String content,@Param("contentTime") Date contentTime);
	
	public List<Comment> findCommentsByReadId(Integer readId); 

}
