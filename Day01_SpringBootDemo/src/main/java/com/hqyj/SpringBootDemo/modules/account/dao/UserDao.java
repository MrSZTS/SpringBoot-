package com.hqyj.SpringBootDemo.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.SpringBootDemo.modules.account.entity.User;

@Mapper
public interface UserDao {
	
	//用户注册
	@Insert("insert into user (user_name,password,create_date)"
			+" values (#{userName},#{password},#{createDate})")
	@Options(useGeneratedKeys = true,keyColumn = "user_id",keyProperty = "userId")
	void insertUser(User user);
	
	//查询用户是否存在
	@Select("select * from user where user_name = #{userName}")
	User getUserByUserName(String userName);
	
	@Update("update user set user_name = #{userName} where user_id = #{userId}")
	void updateUser(User user);
	
	@Delete("delete from user where user_id = #{userId}")
	void deleteUser(int userId);
	
}
