package com.pm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.pm.dao.CollectMapper;
import com.pm.dao.CommentMapper;
import com.pm.dao.ReadMapper;
import com.pm.dao.ReplyMapper;
import com.pm.dao.ZanNumMapper;
import com.pm.entity.Comment;
import com.pm.entity.Read;
import com.pm.entity.Reply;

@Service
public class ReadService {
	@Resource
	private ReadMapper readMapper;
	
	@Resource
	private ZanNumMapper zanNumMapper;
	
	@Resource
	private CollectMapper collectMapper;
	
	@Resource 
	private CommentMapper commentMapper;
	
	@Resource
	private ReplyMapper replyMapper;
	
	public String findAll() {
		List<Read> reads = this.readMapper.findAll();
		for (int i = 0; i < reads.size(); i++) {
			int readId = reads.get(i).getId();
			List<Comment> comments = this.commentMapper.findCommentsByReadId(readId);
			reads.get(i).setComments(comments);
			for (int j = 0; j < comments.size(); j++) {
				int commentId = comments.get(j).getId();
				List<Reply> replies = this.replyMapper.queryReplyByCommentId(commentId);
				comments.get(j).setReplies(replies);
			}
		}
		return JSON.toJSONString(reads);
	}
	
	//对文章进行点赞
	public String addZanNumByRead(int readId, int userId, int zanNumAfter){
        int n =this.zanNumMapper.insertZanByRead(readId, userId);
        if(n>0){
            int m =this.readMapper.updateZanNumByRead(readId, zanNumAfter);
            if(m>0) return "true";
            else return "fasle";
        }else {
            return "false";
        }
    }
	
	//对文章取消点赞
    public String decZanNumByRead(int readId, int userId, int zanNumAfter){
        int n =this.zanNumMapper.delZanByRead(readId, userId);
        if(n>0){
            int m =this.readMapper.updateZanNumByRead(readId, zanNumAfter);
            if(m>0) return "true";
            else return "fasle";
        }else {
            return "false";
        }
    }
    
    //收藏动态
	public String addCollect(int readId, int userId){
	    int result = this.collectMapper.insertCollect(readId,userId);
	    if(result>0){
	        return "true";
	    }else{
	        return "false";
	    }
	}
	//取消收藏动态
	public String decCollect(int readId, int userId){
	    int result = this.collectMapper.deleteCollect(readId,userId);
	    if(result>0){
	        return "true";
	    }else{
	        return "false";
	    }
	}
	
	//我的收藏
	public List<Read> findMyCollect(Integer userId) {
		List<Read> reads = this.collectMapper.findMyCollect(userId);
		return reads;
	}
	
	//添加评论
	public String addComment(Integer readId,Integer userId,String content,String contentTime) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(contentTime);
		int result = this.commentMapper.insertComment(readId, userId, content, date);
		if (result > 0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	//给评论添加回复
	public String addReply(Integer commentId,Integer fromId,Integer toId,String replyContent,String replyTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(replyTime);
        int result = this.replyMapper.insertReply(commentId,fromId,toId,replyContent,date);
        if(result>0){
            return "true";
        }else{
            return "false";
        }
    }
	
	
	//查询某一文章的评论以及回复
	public List<Comment> findCommentByReadId(Integer readId) {
		List<Comment> comments = this.commentMapper.findCommentsByReadId(readId);
		for (int j = 0; j < comments.size(); j++) {
			int commentId = comments.get(j).getId();
			List<Reply> replies = this.replyMapper.queryReplyByCommentId(commentId);
			comments.get(j).setReplies(replies);
		}
		return comments;
	}
	
}
