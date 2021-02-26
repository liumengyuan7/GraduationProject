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
	private NewsMapper newsMapper;
	@Resource
	private ZanNumMapper zanNumMapper;
	
	public List<News> findAll(){
		return this.newsMapper.findAllNews();
	}
	
	public int insertNews(News read) {
		return this.newsMapper.insertNews(read);
	}
	
	public News findNewsByUniqueKey(String uniquekey) {
		return this.newsMapper.findNewsByUniqueKey(uniquekey);
	}
	
	public List<News> findNewsByType(String type){
		return this.newsMapper.findNewsByType(type);
	}
	
	//对新闻进行点赞
	public String addZanNumByNews(int newsId, int userId, int zanNumAfter){
        int n =this.zanNumMapper.insertZanByNews(newsId, userId);
        if(n>0){
            int m =this.newsMapper.updateZanNumByNews(newsId,zanNumAfter);
            if(m>0) return "true";
            else return "fasle";
        }else {
            return "false";
        }
    }
	
	//对新闻取消点赞
    public String decZanNumByNews(int newsId, int userId, int zanNumAfter){
        int n =this.zanNumMapper.delZanByNews(newsId, userId);
        if(n>0){
            int m =this.newsMapper.updateZanNumByNews(newsId,zanNumAfter);
            if(m>0) return "true";
            else return "fasle";
        }else {
            return "false";
        }
    }

}
