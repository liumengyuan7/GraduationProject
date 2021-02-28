package com.pm.controller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pm.entity.Comment;
import com.pm.entity.Read;
import com.pm.service.ReadService;

@Controller
@RequestMapping("read")
public class ReadController {
	@Resource
	private ReadService readService;
	
	/**
	 * 查询所有文章
	 * @return
	 */
	@RequestMapping(value="findAllRead",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findAll() {
		String result = this.readService.findAll();
		if (result == null || result.equals("")) {
			return "false";
		}else {
			return result;
		}
	}
	
	/**
	 * 对文章进行点赞
	 * @param readId
	 * @param userId
	 * @param zanNumAfter
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/addZanNum")
	public  String addZanNum(@RequestParam("readId") int readId,
                   @RequestParam("userId") int userId,
                   @RequestParam("zanNumAfter") int zanNumAfter){
	  String result = this.readService.addZanNumByRead(readId,userId,zanNumAfter);
	  return result;
	}	
	
	/**
	    * 对文章取消点赞
	   * @param readId 文章id
	   * @param userId 用户id
	   * @param zanNumAfter 当前点赞总数
	   * @return true操作成功，false操作失败
	   */
	   @ResponseBody
	   @RequestMapping("/decZanNum")
	  public  String decZanNum(@RequestParam("readId") int readId,
	                       @RequestParam("userId") int userId,
	                       @RequestParam("zanNumAfter") int zanNumAfter){
	      String result = this.readService.decZanNumByRead(readId,userId,zanNumAfter);
	      return result;
	  }
	   
	   
	/**
	 * 添加收藏
	 * @param readId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addCollect")
	public  String addCollect(@RequestParam("readId") Integer readId,@RequestParam("userId") Integer userId){
	    String result = this.readService.addCollect(readId,userId);
	    return result;
	}

	/**
	 * 删除收藏
	 * @param readId
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/decCollect")
	public  String decCollect(@RequestParam("readId") Integer readId,@RequestParam("userId") Integer userId){
	    String result = this.readService.decCollect(readId,userId);
	    return result;
	}
	
	/**
	 * 我的收藏
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/myCollect")
	public String findMyCollect(@RequestParam("userId")Integer userId) {
		List<Read> reads = this.readService.findMyCollect(userId);
		for (int i = 0; i < reads.size(); i++) {
			Integer readId = reads.get(i).getId();
			List<Comment> comments = this.readService.findCommentByReadId(readId);
			reads.get(i).setComments(comments);
		}
		return JSON.toJSONString(reads);
	}
	
	/**
	 * 添加评论
	 * @param readId 文章id
	 * @param userId 当前用户id
	 * @param content 评论内容
	 * @param contentTime 评论时间
	 * @return true：评论成功 false：评论失败
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/addComment")
	public String addComment(@RequestParam("readId") Integer readId,
            @RequestParam("userId") Integer userId,
            @RequestParam("content") String content,@RequestParam("contentTime") String contentTime) throws ParseException {
		String result = this.readService.addComment(readId, userId, content, contentTime);
		return result;
	}
	
	/**
	 * 评论增加回复信息
	 * @param commentId 评论ID
	 * @param fromId
	 * @param toId
	 * @param replyContent 回复的用户id
	 * @param replyTime 回复时间
	 * @return true：评论成功 false：评论失败
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addReply")
	public String addReply(@RequestParam("commentId") Integer commentId,
            @RequestParam("fromId") Integer fromId,
            @RequestParam("toId") Integer toId,
            @RequestParam("replyContent") String replyContent,@RequestParam("replyTime") String replyTime) throws Exception {
		String result = this.readService.addReply(commentId, fromId, toId, replyContent,replyTime);
		return result;
	}
}
	
	
