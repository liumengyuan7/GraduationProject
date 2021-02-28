package com.pm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pm.entity.Reply;

public interface ReplyMapper {
	public List<Reply> queryReplyByCommentId(Integer commentId);
	public int insertReply(@Param("commentId")Integer commentId,@Param("fromId")Integer fromId,@Param("toId")Integer toId,
			@Param("replyContent")String replyContent,@Param("replyTime")Date replyTime);
}
