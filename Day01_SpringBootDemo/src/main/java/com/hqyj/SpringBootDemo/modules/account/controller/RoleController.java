package com.hqyj.SpringBootDemo.modules.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.account.service.RoleService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

@Controller
@RequestMapping("/account")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	/**
	 * 127.0.0.1/account/role	----post
	 */
	@RequestMapping(value = "/role",consumes = "application/json")
	public Result<Role> insertRole(@RequestBody Role role) {
		return roleService.insertRole(role);
	}
	
}
