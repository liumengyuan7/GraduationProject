package com.pm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pm.entity.Read;

public interface CollectMapper {
	//收藏
    public int insertCollect(@Param("readId") Integer readId,@Param("userId") Integer userId);

    //取消收藏
    public int deleteCollect(@Param("readId") Integer readId,@Param("userId") Integer userId);
    
    //我的收藏
    public List<Read> findMyCollect(Integer userId);
    
    //我的收藏数量
    public int countMyCollect(Integer userId);
    
    //某一文章的收藏数
    public int countCollectNum(Integer readId);
}
