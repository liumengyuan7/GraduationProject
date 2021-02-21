package com.pm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pm.entity.Joke;

public interface JokeMapper {
	public List<Joke> findAll(@Param("pagenum")int pagenum,@Param("pagesize")int pagesize);
	
	public Joke findJokeById(int id);
	
	public int insertJoke(Joke joke);
	
	public int countAllJokes();

	public List<Joke> findAllJokes();
	
	public int updateZanNumByJoke(@Param("jokeId") int jokeId,@Param("zanNum") int zanNum);
	
	//根据点赞情况返回笑话
	public Joke findJokeByZanTop();
}
