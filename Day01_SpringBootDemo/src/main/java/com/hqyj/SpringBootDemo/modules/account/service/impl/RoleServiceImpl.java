package com.hqyj.SpringBootDemo.modules.account.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.SpringBootDemo.modules.account.dao.RoleDao;
import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.account.service.RoleService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Result<Role> insertRole(Role role) {
		roleDao.insertRole(role);
		return new Result<Role>(ResultStatus.SUCCESS.status,"insert success",role);
	}

		
	
	@Override
	public List<Role> getRoles() {
		return Optional.ofNullable(roleDao.getRoles()).orElse(Collections.emptyList());
	}
	
}
