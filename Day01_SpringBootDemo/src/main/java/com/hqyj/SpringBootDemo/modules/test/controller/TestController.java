package com.hqyj.SpringBootDemo.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	/**
	 * 127.0.0.1:8080/test/desc
	 * @return
	 */
	@RequestMapping("/test/desc")	
	@ResponseBody
	//返回的是一个接口，而不是一个页面控制器
	public String testDesc() {
		return "This is test module desc";
	}
	
}
