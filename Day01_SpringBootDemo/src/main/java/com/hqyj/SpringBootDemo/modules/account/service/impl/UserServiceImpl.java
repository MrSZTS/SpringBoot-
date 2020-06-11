package com.hqyj.SpringBootDemo.modules.account.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqyj.SpringBootDemo.modules.account.dao.UserDao;
import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;
import com.hqyj.SpringBootDemo.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional//遇到异常回滚
	public Result<User> insertCity(User user) {
		User userTemp = getUserByUserName(user.getUserName());
		if(userTemp != null) {
			return new Result<User>(ResultStatus.FAILD.status,"userName is repeat");
		}
		
		user.setCreateDate(new Date());
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		
		
		userDao.insertUser(user);
		return new Result<User>(ResultStatus.SUCCESS.status,"insert success",user);
	}
	
	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	@Override
	public Result<User> updateUser(User user) {
		userDao.updateUser(user);
		return new Result<User>(ResultStatus.SUCCESS.status,"update success",user);
	}

	@Override
	public Result<Object> deleteUser(int userId) {
		userDao.deleteUser(userId);
		return new Result<Object>(ResultStatus.SUCCESS.status,"delete success");
	}




	
}
