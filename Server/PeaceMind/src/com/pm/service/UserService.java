package com.pm.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.pm.dao.UserMapper;
import com.pm.entity.User;

@Service
public class UserService {
	@Resource
	private UserMapper userMapper;
	
	public List<User> findAll(){
		return this.userMapper.findAll();
	}
	
	/**
	 * 用户注册
	 * @param userPhone
	 * @param userPassword
	 * @param userNickname
	 * @param userSex
	 * @return 注册成功：id  注册失败：false  已注册：repeat
	 */
	public String userRegister(String userPhone,String userPassword,String userNickname,String userSex){
		Map userConfirm = this.userMapper.findUserByPhone(userPhone);
		if(userConfirm == null){
			//添加用户
			User user = new User();
			user.setUser_register_time(new Timestamp(new Date().getTime()));
			user.setUser_nickname(userNickname);
			user.setUser_password(userPassword);
			user.setUser_phone(userPhone);
			user.setUser_sex(userSex);
			int result = this.userMapper.insertUserInfo(user);
			if(result>0 ) { 
				return user.getId()+"";
			}
			else {
				return "false";
			}
		}else {
			//如果注册用户信息重复，则会返回repeat
			return "repeat";
		}
	}
	
	//用户的登录操作
	public String userLogin(String phone, String password) {
		Map user = this.userMapper.selectUserByPhoneAndPassword(phone, password); //查询是否存在用户
		if(user != null){
			List<Map> userList = new ArrayList<>();
			userList.add(user);
			//返回用户信息和兴趣的字符串
			return JSON.toJSONString(userList);
		}
		return null;
	}

	//用户修改密码的操作
	public boolean updatePassword(String id,String password){
		int result = this.userMapper.updateUserPasswordById(id,password);
		if(result>0) return true;
		else return false;
	}

	//用户昵称更新的操作
	public boolean updateNickname(String id,String nickname){
		int result = this.userMapper.updateUserNicknameById(id,nickname);
		if(result>0) return true;
		else return false;
	}
}
