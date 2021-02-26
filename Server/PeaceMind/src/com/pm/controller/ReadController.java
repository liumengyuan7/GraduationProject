package com.pm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
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
	public String findAllRead() {
		List<Read> reads = this.readService.findAll();
		return JSON.toJSONString(reads);
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
		return JSON.toJSONString(reads);
	}
	
	
}
