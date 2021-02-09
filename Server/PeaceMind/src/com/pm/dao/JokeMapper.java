package com.pm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pm.entity.Joke;

public interface JokeMapper {
	public List<Joke> findAll(@Param("pagenum")int pagenum,@Param("pagesize")int pagesize);
	
	public Joke findJokeById(Integer id);
	
	public int insertJoke(Joke joke);
	
	public int countAllJokes();
}
