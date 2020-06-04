package com.hqyj.SpringBootDemo.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hqyj.SpringBootDemo.modules.test.entity.City;

@Mapper
public interface CityDao {
	
	/**
	 * 配置文件方式
	 * application.properties
	 * mybatis.type-aliases-package=com.hqyj.demo.modules.*.entity
	 * mybatis.mapper-locations=classpath:mapper/*Mapper.xml
	 * 读取cityMapper.xml，方法名和mapper中设置的id一致
	 */
	List<City> getCitiesByCountryId(int countryId);
		
	/**
	 * #{countryId} ---- prepared statement, select * from m_city where country_id = ?
	 * ${countryId} ---- statement, select * from m_city where country_id = 1
	 */
	@Select("select * from m_city where country_id = #{countryId}")
	List<City> getCitiesByCountryId2(int countryId);
	
	@Select("select * from m_city where city_name=#{cityName} and local_city_name=#{localCityName}")
	City getCityByName(@Param("cityName") String cityName1,String localCityName);
	
}
