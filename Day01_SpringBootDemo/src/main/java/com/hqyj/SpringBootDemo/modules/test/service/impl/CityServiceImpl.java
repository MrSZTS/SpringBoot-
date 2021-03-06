package com.hqyj.SpringBootDemo.modules.test.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.dao.CityDao;
import com.hqyj.SpringBootDemo.modules.test.entity.City;
import com.hqyj.SpringBootDemo.modules.test.service.CityService;
import com.hqyj.SpringBootDemo.utils.RedisUtils;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityDao cityDao;
	
	//redisUtils被注册为组件，并且将配置类中的redisTemplate注入进来了
	@Autowired
	private RedisUtils redisUtils;
	
	@Override
	public List<City> getCitiesByCountryId(int countryId) {
		//return cityDao.getCitiesByCountryId(countryId);
		return Optional.ofNullable(cityDao.getCitiesByCountryId2(countryId))
				.orElse(Collections.emptyList());
	}

	@Override
	public City getCityByName(String cityName, String localCityName) {
		return cityDao.getCityByName(cityName, localCityName);
	}

	@Override
	public PageInfo<City> getCitiesPage(int currentPage, int pageSize, int countryId) {
		PageHelper.startPage(currentPage,pageSize);
		return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesByCountryId2(countryId))
				.orElse(Collections.emptyList()));
	}

	@Override
	public PageInfo<City> getCitiesBySearchVo(SearchVo serarchVo) {
		serarchVo.initSearchVo();
		PageHelper.startPage(serarchVo.getCurrentPage(),serarchVo.getPageSize());
		
		return new PageInfo<City>(
				Optional.ofNullable(cityDao.getCitiesBySearchVo(serarchVo))
				.orElse(Collections.emptyList()));
	}

	@Override
	public Result<City> insertCity(City city) {
		city.setDateCreated(new Date());
		cityDao.insertCity(city);
		return new Result<City>(ResultStatus.SUCCESS.status , "insert success", city);
	}

	@Override	
	@Transactional
	/*
	 * noRollbackFor			遇到什么异常，不回滚
	 * rollbackFor				遇到什么异常，回滚
	 * rollbackForClassName		遇到某个类名的时候就回滚
	 * propagation				传播方式
	 */	
	public Result<City> updateCity(City city) {
		cityDao.updateCity(city);
		//int a = 7/0;
		return new Result<City>(ResultStatus.SUCCESS.status,"update success",city);
	}

	@Override
	public Result<Object> deleteCity(int cityId) {
		cityDao.deleteCity(cityId);
		return new Result<Object>(ResultStatus.SUCCESS.status,"delete success");
	}

	@Override
	public Object migrateCitiesByCounteryId(int countryId) {
		List<City> cities = getCitiesByCountryId(countryId);
		redisUtils.set("cities", cities);		
		return redisUtils.get("cities");
	}

	

}
