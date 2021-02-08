package com.pm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pm.entity.User;

public interface UserMapper {
	public List<User> findAll();
	
	public Map findUserByPhone(String phone);
	
	public User findUserById(int id);
	
	public int insertUserInfo(User user);

	public Map selectUserByPhoneAndPassword(@Param("phone")String phone, @Param("password")String password);

	public int updateUserPasswordById(@Param("id")String id, @Param("password")String password);

	public int updateUserNicknameById(@Param("id")String id, @Param("nickname")String nickname);
}
