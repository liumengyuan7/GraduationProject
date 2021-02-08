package com.pm.dao;

import java.util.List;

import com.pm.entity.User;

public interface UserMapper {
	public List<User> findAll();
}
