package com.hqyj.SpringBootDemo.modules.account.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.shiro.subject.Subject;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.config.ResourceConfigBean;
import com.hqyj.SpringBootDemo.modules.account.dao.UserDao;
import com.hqyj.SpringBootDemo.modules.account.dao.UserRoleDao;
import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.account.entity.User;
import com.hqyj.SpringBootDemo.modules.account.service.UserService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.utils.FileUtil;
import com.hqyj.SpringBootDemo.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService{

	private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private ResourceConfigBean resourceConfigBean;


//	  @Override	  
//	  @Transactional//事务：遇到异常回滚 
//	  public Result<User> insertCity(User user) { 
//			User userTemp = getUserByUserName(user.getUserName());
//	  //判断条件：这样的判断条件可以做到用户在不修改用户名的情况下，修改用户的角色 
//	  if(userTemp != null && userTemp.getUserId() != user.getUserId()) { return new
//	  Result<User>(ResultStatus.FAILD.status,"userName is repeat"); }
//	  
//	  user.setCreateDate(new Date());
//	  user.setPassword(MD5Util.getMD5(user.getPassword()));
//	  
//	  userDao.insertUser(user);
//	  
//	  userRoleDao.deleteRolesByUserId(user.getUserId()); List<Role> roles =
//	  user.getRoles(); 
//	  if(roles != null && roles.size() > 0) { 
//		  for (Role role : roles) { 
//			  userRoleDao.insertUserRole(user.getUserId(), role.getRoleId()); 
//			  } 
//		  }	  
//	  return new Result<User>(ResultStatus.SUCCESS.status,"insert success",user); 
//	  }
	 
	
	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	@Override
	public Result<User> login(User user) {
//		User userTemp = userDao.getUserByUserName(user.getUserName());
//		if(userTemp == null || !userTemp.getPassword().equals(MD5Util.getMD5(user.getPassword()))) {
//			return new Result<User>(ResultStatus.FAILED.status,"username or password error.");
//		}
		
		try {
			//拿到subject组件 	subject是属于应用层代码和shiro框架交互的东西
			Subject subject = SecurityUtils.getSubject();
			//包装令牌
			UsernamePasswordToken usernamePasswordToken = 
					new UsernamePasswordToken(user.getUserName(),MD5Util.getMD5(user.getPassword()));
			usernamePasswordToken.setRememberMe(user.getRememberMe());
			
			//调用login将令牌传入到MyRealm中的身份验证
			subject.login(usernamePasswordToken);
			subject.checkRoles();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<User>(ResultStatus.FAILED.status,"user name or password error.");
		}	
		
		return new Result<User>(ResultStatus.SUCCESS.status,"login success.",user);
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
	public User getUserByUserId(int userId) {
		return userDao.getUserByUserId(userId);
	}	
	
	/*
	 * @Override
	 * 
	 * @Transactional public Result<User> updateUser(User user) { User userTemp =
	 * getUserByUserName(user.getUserName()); if(userTemp != null) { return new
	 * Result<User>(ResultStatus.FAILD.status,"User name is repeat"); }
	 * 
	 * userDao.updateUser(user);
	 * 
	 * userRoleDao.deleteRolesByUserId(user.getUserId()); List<Role> roles =
	 * user.getRoles(); if(roles != null && roles.size() > 0) { for (Role role :
	 * roles) { userRoleDao.insertUserRole(user.getUserId(), role.getRoleId()); } }
	 * 
	 * return new Result<User>(ResultStatus.SUCCESS.status,"update success",user); }
	 */

	@Override
	public Result<Object> deleteUser(int userId) {
		userDao.deleteUser(userId);
		userRoleDao.deleteRolesByUserId(userId);
		return new Result<Object>(ResultStatus.SUCCESS.status,"delete success");
	}

	@Override
	@Transactional
	public Result<User> editUser(User user) {
		User userTemp = getUserByUserName(user.getUserName());
		if(userTemp != null && userTemp.getUserId() != user.getUserId()) {
			return new Result<User>(ResultStatus.FAILED.status,"User name is repeat");
		}
		
		if(user.getUserId() > 0) {
			userDao.updateUser(user);
			userRoleDao.deleteRolesByUserId(user.getUserId());
		}else {
			userDao.insertUser(user);
		}
				
		List<Role> roles = user.getRoles();
		if(roles != null && roles.size() > 0) {
			for (Role role : roles) {
				userRoleDao.insertUserRole(user.getUserId(), role.getRoleId());
			}
		}
		
		return new Result<User>(ResultStatus.SUCCESS.status,"Edit success",user);

	}

	@Override
	public Result<String> uploadUserImage(MultipartFile userImage) {
		
		if (userImage.isEmpty()) {//是不是为空
			return new Result<>(ResultStatus.FAILED.status, "User image is empty.");
		}
		if (!FileUtil.isImage(userImage)) {//判断传过来的文件是不是image(判断是否有长和宽)
			return new Result<>(ResultStatus.FAILED.status, "File is not a image.");
		}
		
		//原始的文件名 
		String originalFilename = userImage.getOriginalFilename();
		//相对路径
		String relatedPath = resourceConfigBean.getResourcePath() + originalFilename;
		//目标文件的路径（从资源配置中读取		resourceConfigBean.getLocalPathForWindows()	）
		//目标文件路径要放置这个文件的位置 
		String destPath = String.format("%s%s", resourceConfigBean.getLocalPathForWindows(), originalFilename);
		try {
			File destFile = new File(destPath);
			//通过方法transferTo将上传的文件写道目标文件的位置路径
			userImage.transferTo(destFile);
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
			return new Result<>(ResultStatus.FAILED.status, "File upload error.");
		}
		
		return new Result<>(ResultStatus.SUCCESS.status, "File upload success.", relatedPath);
	}

	@Override
	@Transactional
	public Result<User> updateUserProfile(User user) {
		User userTemp = getUserByUserName(user.getUserName());
		if (userTemp != null && userTemp.getUserId() != user.getUserId()) {
			return new Result<User>(ResultStatus.FAILED.status, "User name is repeat.");
		}

		userDao.updateUser(user);

		return new Result<User>(ResultStatus.SUCCESS.status, "Edit success.", user);
	}

	@Override
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}

	





	
}
