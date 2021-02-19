package com.pm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.dao.JokeMapper;
import com.pm.dao.ZanNumMapper;
import com.pm.entity.Joke;
import com.pm.entity.User;

@Service
public class JokeService {
	@Resource
	private JokeMapper mapper;
	@Autowired
	private ZanNumMapper zanNumMapper;
	
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
	
	//对笑话进行点赞
	public String addZanNumByJoke(int jokeId, int userId, int zanNumAfter){
        int n =this.zanNumMapper.insertZan(jokeId,userId);
        if(n>0){
            int m =this.mapper.updateZanNumByJoke(jokeId,zanNumAfter);
            if(m>0) return "true";
            else return "fasle";
        }else {
            return "false";
        }
    }
    //对笑话取消点赞
    public String decZanNumByJoke(int jokeId, int userId, int zanNumAfter){
        int n =this.zanNumMapper.delZan(jokeId,userId);
        if(n>0){
            int m =this.mapper.updateZanNumByJoke(jokeId,zanNumAfter);
            if(m>0) return "true";
            else return "fasle";
        }else {
            return "false";
        }
    }
}
