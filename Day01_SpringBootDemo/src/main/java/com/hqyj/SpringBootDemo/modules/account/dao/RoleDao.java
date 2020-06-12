package com.hqyj.SpringBootDemo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.hqyj.SpringBootDemo.modules.account.entity.Role;

@Mapper
public interface RoleDao {

	//增
	@Insert("insert into role (role_name) values (#{roleName})")
	@Options(useGeneratedKeys = true,keyColumn = "role_id",keyProperty = "roleId")
	void insertRole(Role role);
	
	//查
	@Select("select * from role where role_name = #{roleName}")
	Role getRoleByRoleName(String roleName);
	
	//不需要任何信息，查到所有角色
	@Select("select * from role")
	List<Role> getRoles();
	
	//删
	
	
	//改
	
	
}
