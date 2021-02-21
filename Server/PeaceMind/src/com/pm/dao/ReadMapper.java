package com.pm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pm.entity.Joke;
import com.pm.entity.Read;

public interface ReadMapper {
	public int insertRead(Read read);
	
	public List<Read> findAllRead();
	
	public List<Read> findReadByType(String type);
	
	public Read findReadByUniqueKey(String uniquekey);
	
	public int updateZanNumByRead(@Param("readId") int readId,@Param("zanNum") int zanNum);

}
