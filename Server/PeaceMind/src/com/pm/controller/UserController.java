package com.pm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pm.entity.User;
import com.pm.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping("list")
	@ResponseBody
	public String findAll() {
		List<User> users=userService.findAll();
		System.out.println(users.toString());
		return JSON.toJSONString(users);
	}
}
