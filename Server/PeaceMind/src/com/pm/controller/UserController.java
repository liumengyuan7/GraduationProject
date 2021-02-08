package com.pm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.entity.User;
import com.pm.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("list")
	public String findAll(Model model) {
		List<User> users=userService.findAll();
		System.out.println(users.toString());
		model.addAttribute("users", users);
		return "list";
	}
}
