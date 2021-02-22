package com.pm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pm.dao.NewsMapper;
import com.pm.dao.ZanNumMapper;
import com.pm.entity.News;

@Service
public class NewsService {
	@Resource
	private NewsMapper readMapper;
	@Resource
	private ZanNumMapper zanNumMapper;
	
	public List<News> findAll(){
		return this.readMapper.findAllNews();
	}
	
	public int insertNews(News read) {
		return this.readMapper.insertNews(read);
	}
	
	public News findNewsByUniqueKey(String uniquekey) {
		return this.readMapper.findNewsByUniqueKey(uniquekey);
	}
	
	public List<News> findNewsByType(String type){
		return this.readMapper.findNewsByType(type);
	}
	
	//对新闻进行点赞
	public String addZanNumByNews(int readId, int userId, int zanNumAfter){
        int n =this.zanNumMapper.insertZan(readId,userId);
        if(n>0){
            int m =this.readMapper.updateZanNumByNews(readId,zanNumAfter);
            if(m>0) return "true";
            else return "fasle";
        }else {
            return "false";
        }
    }
	
	//对新闻取消点赞
    public String decZanNumByNews(int readId, int userId, int zanNumAfter){
        int n =this.zanNumMapper.delZan(readId,userId);
        if(n>0){
            int m =this.readMapper.updateZanNumByNews(readId,zanNumAfter);
            if(m>0) return "true";
            else return "fasle";
        }else {
            return "false";
        }
    }

}
