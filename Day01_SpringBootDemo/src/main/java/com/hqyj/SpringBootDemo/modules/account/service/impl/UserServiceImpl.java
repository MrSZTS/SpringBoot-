package com.hqyj.SpringBootDemo.modules.account.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.modules.account.dao.UserDao;
import com.hqyj.SpringBootDemo.modules.account.dao.UserRoleDao;
import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;

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
		
		userRoleDao.deleteRolesByUserId(user.getUserId());
		List<Role> roles = user.getRoles();
		if(roles != null && roles.size() > 0) {
			for (Role role : roles) {
				userRoleDao.insertUserRole(user.getUserId(), role.getRoleId());
			}
		}
		
		return new Result<User>(ResultStatus.SUCCESS.status,"insert success",user);
	}
	
	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	@Override
	public Result<User> login(User user) {
		User userTemp = userDao.getUserByUserName(user.getUserName());
		if(userTemp == null || !userTemp.getPassword().equals(MD5Util.getMD5(user.getPassword()))) {
			return new Result<User>(ResultStatus.FAILD.status,"username or password error.");
		}
		return new Result<User>(ResultStatus.SUCCESS.status,"login success.",userTemp);
	}
	
	@Override
	public PageInfo<User> getCitiesBySearchVo(SearchVo serarchVo) {
		//当前页没有传当前页和pagesize过来，给它一个初始化值
		serarchVo.initSearchVo();
		PageHelper.startPage(serarchVo.getCurrentPage(),serarchVo.getPageSize());
		return new PageInfo<User>(
				Optional.ofNullable(userDao.getCitiesBySearchVo(serarchVo))
				.orElse(Collections.emptyList()));
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
