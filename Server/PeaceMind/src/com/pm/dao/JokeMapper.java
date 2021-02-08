package com.pm.dao;

import java.util.List;

import com.pm.entity.Joke;

public interface JokeMapper {
	public List<Joke> findAll();
	
	public Joke findJokeById(Integer id);
	
	public int insertJoke(Joke joke);
}
