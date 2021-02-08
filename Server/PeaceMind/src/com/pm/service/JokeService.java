package com.pm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.dao.JokeMapper;
import com.pm.entity.Joke;
import com.pm.entity.User;

@Service
public class JokeService {
	@Autowired
	private JokeMapper mapper;
	
	public List<Joke> findAll(){
		return mapper.findAll();
	}
	
	public Joke findJokeById(Integer jokeId) {
		return mapper.findJokeById(jokeId);
	}
	
	public int insertJoke(Joke joke) {
		return mapper.insertJoke(joke);
	}
}
