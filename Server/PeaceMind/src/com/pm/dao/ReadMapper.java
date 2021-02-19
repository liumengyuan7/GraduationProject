package com.pm.dao;

import java.util.List;

import com.pm.entity.Joke;
import com.pm.entity.Read;

public interface ReadMapper {
	public int insertRead(Read read);
	
	public List<Read> findAllRead();
	
	public Read findReadByUniqueKey(String uniquekey);
	
}
