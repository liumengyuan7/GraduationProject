package com.pm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pm.entity.Read;

public interface ReadMapper {
	//查询所有文章
	public List<Read> findAll();
	
	//更新点赞
	public int updateZanNumByRead(@Param("readId") int readId,@Param("zanNum") int zanNum);

}
