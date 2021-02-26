package com.pm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pm.dao.CollectMapper;
import com.pm.dao.ReadMapper;
import com.pm.dao.ZanNumMapper;
import com.pm.entity.Read;

@Service
public class ReadService {
	@Resource
	private ReadMapper readMapper;
	
	@Resource
	private ZanNumMapper zanNumMapper;
	
	@Resource
	private CollectMapper collectMapper;
	
	public List<Read> findAll() {
		return this.readMapper.findAll();
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
}
