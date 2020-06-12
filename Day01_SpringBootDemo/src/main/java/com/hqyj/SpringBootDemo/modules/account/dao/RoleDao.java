package com.hqyj.SpringBootDemo.modules.account.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hqyj.SpringBootDemo.modules.account.entity.Role;

@Mapper
public interface RoleDao {

	//增
	@Insert("insert into role (role_name) values (#{roleName})")
	void insertRole(Role role);
	
	//查
	@Select("select * from role where role_name = #{roleName}")
	Role getRoleByRoleName(String roleName);
	
	//删
	
	
	//改
	
	
}
