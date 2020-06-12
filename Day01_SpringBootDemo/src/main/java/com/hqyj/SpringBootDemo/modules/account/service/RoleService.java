package com.hqyj.SpringBootDemo.modules.account.service;

import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

public interface RoleService {

	Result<Role> insertRole(Role role);
	
}
