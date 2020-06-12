package com.hqyj.SpringBootDemo.modules.account.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.hqyj.SpringBootDemo.modules.account.entity.Resource;
import com.hqyj.SpringBootDemo.modules.account.entity.Role;

@Mapper
public interface ResourceDao {
	
	//增
	@Insert("insert into resource (resource_uri,resource_name,permission) "
				+ "values (#{resourceUri},#{resourceName},#{permission})")
	void insertResource(Resource resource);
	
	//查
	
	
	//删
	
	
	//改
	
	
}
