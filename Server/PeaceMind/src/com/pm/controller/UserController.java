package com.pm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/**
	 * 用户注册
	 * @param userPhone
	 * @param userPassword
	 * @param userNickname
	 * @param userSex
	 * @return 注册成功：id  注册失败：false  已注册：repeat
	 */
	@ResponseBody
	@RequestMapping("userRegister")
	public String userRegister(@RequestParam("userPhone") String userPhone,
							@RequestParam("userPassword") String userPassword,
							@RequestParam("userNickname") String userNickname,
							@RequestParam("userSex") String userSex) {
		System.out.println(userPhone+userPassword+userNickname+userSex);
		String result = this.userService.userRegister(userPhone,userPassword,userNickname,userSex);
		if(result.equals("repeat")){
			return "repeat";
		}else if(result.equals("false")){
			return "false";
		}else {
			return result;
		}
	}
	
	
	//用户登录
	@ResponseBody
	@RequestMapping(value = "userLogin",produces = "text/html;charset=UTF-8")
	public String userLogin(@RequestParam("userphone") String phone,
							@RequestParam("password") String password) {
		String result = this.userService.userLogin(phone, password);
		if(result==null) {
			return "false";
		}else {
			System.out.println(result);
			return result;
		}
	}
	
	
	//用户密码的修改
	@ResponseBody
	@RequestMapping("updatePassword")
	public String updatePassword(@RequestParam("userId") String id,
							   @RequestParam("password") String password){
		boolean result = this.userService.updatePassword(id,password);
		if(result){
			return "true";
		}else{
			return "false";
		}
	}

	//用户昵称的修改
	@ResponseBody
	@RequestMapping("updateNickname")
	public String updateNickname(@RequestParam("userId") String id,
								 @RequestParam("nickname") String nickname){
		boolean result = this.userService.updateNickname(id,nickname);
		if(result){
			return "true";
		}else{
			return "false";
		}
	}
	
	//根据用户手机号修改密码
	//修改成功返回true，失败返回false
	@ResponseBody
	@RequestMapping("updateUserPwdByPhone")
	public String updateUserPwdByPhone(@RequestParam("phone")String phone,
			@RequestParam("password")String password) {
		String result = this.userService.updateUserPwdByPhone(phone, password);
		return result;
	}
	
}
