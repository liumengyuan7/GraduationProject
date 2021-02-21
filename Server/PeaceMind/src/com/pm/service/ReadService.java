package com.pm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pm.dao.ReadMapper;
import com.pm.dao.ZanNumMapper;
import com.pm.entity.Read;

@Service
public class ReadService {
	@Resource
	private ReadMapper readMapper;
	@Resource
	private ZanNumMapper zanNumMapper;
	
	public List<Read> findAll(){
		return this.readMapper.findAllRead();
	}
	
	public int insertRead(Read read) {
		return this.readMapper.insertRead(read);
	}
	
	public Read findReadByUniqueKey(String uniquekey) {
		return this.readMapper.findReadByUniqueKey(uniquekey);
	}
	
	public List<Read> findReadByType(String type){
		return this.readMapper.findReadByType(type);
	}
	
	//对新闻进行点赞
	public String addZanNumByRead(int readId, int userId, int zanNumAfter){
        int n =this.zanNumMapper.insertZan(readId,userId);
        if(n>0){
            int m =this.readMapper.updateZanNumByRead(readId,zanNumAfter);
            if(m>0) return "true";
            else return "fasle";
        }else {
            return "false";
        }
    }
	
	//对新闻取消点赞
    public String decZanNumByRead(int readId, int userId, int zanNumAfter){
        int n =this.zanNumMapper.delZan(readId,userId);
        if(n>0){
            int m =this.readMapper.updateZanNumByRead(readId,zanNumAfter);
            if(m>0) return "true";
            else return "fasle";
        }else {
            return "false";
        }
    }

}
