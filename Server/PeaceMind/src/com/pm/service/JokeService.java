package com.pm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.dao.JokeMapper;
import com.pm.entity.Joke;
import com.pm.entity.User;

@Service
public class JokeService {
	@Resource
	private JokeMapper mapper;
	
	public List<Joke> findAll(int pagenum,int pagesize){
		return mapper.findAll(pagenum, pagesize);
	}
	
	public Joke findJokeById(Integer jokeId) {
		return mapper.findJokeById(jokeId);
	}
	
	public int insertJoke(Joke joke) {
		return mapper.insertJoke(joke);
	}
	
	public int countAllJokes() {
		return this.mapper.countAllJokes();
	}

	public List<Joke> findAllJokes() {
		return this.mapper.findAllJokes();
	}
}
