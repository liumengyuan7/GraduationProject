package com.pm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pm.dao.ReadMapper;
import com.pm.entity.Read;

@Service
public class ReadService {
	@Resource
	private ReadMapper readMapper;
	
	public List<Read> findAll(){
		return this.readMapper.findAllRead();
	}
	
	public int insertRead(Read read) {
		return this.readMapper.insertRead(read);
	}
	
	public Read findReadByUniqueKey(String uniquekey) {
		return this.readMapper.findReadByUniqueKey(uniquekey);
	}

}
