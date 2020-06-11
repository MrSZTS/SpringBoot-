package com.hqyj.SpringBootDemo.modules.account.service;

import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

public interface UserService {

	Result<User> insertCity(User user);
	
	User getUserByUserName(String userName);
	
	Result<User> login(User user);
	
	Result<User> updateUser(User user);
	
	Result<Object> deleteUser(int userId);
	
}
