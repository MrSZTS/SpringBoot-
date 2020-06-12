package com.hqyj.SpringBootDemo.modules.account.service;

import java.util.List;

import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

public interface RoleService {

	Result<Role> insertRole(Role role);
	
	
	
	List<Role> getRoles();
	
}
