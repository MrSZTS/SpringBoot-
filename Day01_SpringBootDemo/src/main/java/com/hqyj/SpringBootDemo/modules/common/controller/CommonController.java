package com.hqyj.SpringBootDemo.modules.common.controller;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {

	@RequestMapping("/dashboard")
	public String dashboardPage() {
		return "index";
	}
	
	@RequestMapping("/403")
	public String errorPageFor403() {
		return "index";
	}
}
