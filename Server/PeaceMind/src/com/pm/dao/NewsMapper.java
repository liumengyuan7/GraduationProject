package com.pm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pm.entity.Joke;
import com.pm.entity.News;

public interface NewsMapper {
	public int insertNews(News read);
	
	public List<News> findAllNews();
	
	public List<News> findNewsByType(String type);
	
	public News findNewsByUniqueKey(String uniquekey);
	
	public int updateZanNumByNews(@Param("newsId") int newsId,@Param("zanNum") int zanNum);

}
