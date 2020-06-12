package com.hqyj.SpringBootDemo.modules.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.SpringBootDemo.modules.account.entity.Role;
import com.hqyj.SpringBootDemo.modules.account.service.RoleService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

@RestController
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
	
	/**
	 * 127.0.0.1/account/roles	----get
	 */
	@RequestMapping("/roles")
	List<Role> getRoles(){
		return roleService.getRoles();
	}
	
}
