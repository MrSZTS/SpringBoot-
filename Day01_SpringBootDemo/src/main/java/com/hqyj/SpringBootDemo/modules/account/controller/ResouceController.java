package com.hqyj.SpringBootDemo.modules.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hqyj.SpringBootDemo.modules.account.entity.Resource;
import com.hqyj.SpringBootDemo.modules.account.service.ResourceService;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

@Controller
@RequestMapping("/account")
public class ResouceController {
	
	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 127.0.0.1/account/resource	----post
	 */
	@RequestMapping(value = "/resource",consumes = "application/json")
	public Result<Resource> insertResource(@RequestBody Resource resource) {
		return resourceService.insertResource(resource);
	}
	
}
