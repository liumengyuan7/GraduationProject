package com.pm.dao;

import org.apache.ibatis.annotations.Param;

public interface ZanNumMapper {
	//得到某一笑话的点赞数
	public int getZanByJokeId(@Param("jokeId") int jokeId);
	
	//对笑话进行点赞
	public int insertZan(@Param("jokeId") int jokeId, @Param("userId") int userId);
		
	//对笑话取消点赞
	public int delZan(@Param("jokeId") int jokeId, @Param("userId") int userId);
	
	//对新闻进行点赞
	public int insertZanByNews(@Param("newsId") int newsId, @Param("userId") int userId);
		
	//对新闻取消点赞
	public int delZanByNews(@Param("newsId") int newsId, @Param("userId") int userId);

	//对文章进行点赞
	public int insertZanByRead(@Param("readId") int readId, @Param("userId") int userId);
		
	//对文章取消点赞
	public int delZanByRead(@Param("readId") int readId, @Param("userId") int userId);

}
