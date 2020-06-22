package com.hqyj.account.modules.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hqyj.account.modules.account.dao.UserDao;
import com.hqyj.account.modules.account.entity.City;
import com.hqyj.account.modules.account.entity.User;
import com.hqyj.account.modules.account.service.UserService;
import com.netflix.discovery.converters.Auto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired//spring提供的远程调用restTemplate
	private RestTemplate restTemplate;
	
	@Override
	public User getUserByUserId(int userId) {
		//return userDao.getUserByUserId(userId);
		User user = userDao.getUserByUserId(userId);
		//url替换为在注册中心注册好的微服务的id
		List<City> cities = restTemplate.getForObject("http://CLIENT-SITE/api/cities/{countryId}", List.class,522);
		user.setCities(cities);
		return user;
	}

}
