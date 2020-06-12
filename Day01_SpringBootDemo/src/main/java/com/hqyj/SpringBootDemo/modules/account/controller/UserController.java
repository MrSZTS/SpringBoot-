package com.hqyj.SpringBootDemo.modules.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;

@RestController
@RequestMapping("/account")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 127.0.0.1/account/user	---------post
	 */
	@PostMapping(value = "/user",consumes = "application/json")
	public Result<User> insertCity(@RequestBody User user) {
		return userService.insertCity(user);
	}
	
	/**
	 * 127.0.0.1/account/login	---------post
	 */
	@PostMapping(value = "/login",consumes = "application/json")
	public Result<User> LoginAuthenticator(@RequestBody User user){
		return userService.login(user);
	}
	
	/**
	 * 127.0.0.1/account/users	---------post
	 */
	@PostMapping(value = "/users",consumes = "application/json")
	public PageInfo<User> getCitiesBySearchVo(@RequestBody SearchVo serarchVo) {
		return userService.getCitiesBySearchVo(serarchVo);
	}
	
	
	
	/**
	 * 127.0.0.1/account/user	---------put
	 */
	@PutMapping(value = "/user",consumes = "application/x-www-form-urlencoded")
	public Result<User> updateUser(@ModelAttribute User user) {
		return userService.updateUser(user);
	}
	
	/**
	 * 127.0.0.1/account/user/1
	 */
	@DeleteMapping(value = "/user/{userId}")
	public Result<Object> deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}
	
}
