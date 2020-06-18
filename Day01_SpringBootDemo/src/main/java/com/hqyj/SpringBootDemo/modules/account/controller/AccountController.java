package com.hqyj.SpringBootDemo.modules.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hqyj.SpringBootDemo.modules.account.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * http://127.0.0.1/account/login
	 */
	@RequestMapping("/login")
	public String loginPage() {
		return "indexSimple";
	}
	
	/**
	 * http://127.0.0.1/account/register
	 */
	@RequestMapping("/register")
	public String registerPage() {
		return "indexSimple";
	}
	
	/**
	 * http://127.0.0.1/account/users
	 */
	@RequestMapping("/users")
	public String usersPage() {
		return "index";
	}
	
	/**
	 * http://127.0.0.1/account/roles
	 */
	@RequestMapping("/roles")
	public String rolesPage() {
		return "index";
	}
	
	/**
	 * http://127.0.0.1/account/resources
	 */
	@RequestMapping("/resources")
	public String resourcesPage() {
		return "index";
	}
	
	/**
	 * http://127.0.0.1/account/profile
	 */
	@RequestMapping("/profile")
	public String profilePage() {
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logOut(ModelMap modelMap) {
		userService.logout();
		modelMap.addAttribute("template", "account/login");
		return "indexSimple";
	}
}
