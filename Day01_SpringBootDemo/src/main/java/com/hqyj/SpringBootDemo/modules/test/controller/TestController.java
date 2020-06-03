package com.hqyj.SpringBootDemo.modules.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.SpringBootDemo.modules.test.vo.ApplicationTest;

@Controller
public class TestController {
		
	@Value("${server.port}")
	private int port;
	@Value("${com.thornBird.name}")
	private String name;
	@Value("${com.thornBird.age}")
	private int age;
	@Value("${com.thornBird.desc}")
	private String desc;
	@Value("${com.thornBird.random}")
	private String random;
	
	@Autowired
	private ApplicationTest applicationTest;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	/**
	 * 127.0.0.1:8080/test/log
	 * @return
	 */
	@RequestMapping("/test/log")
	@ResponseBody
	public String logTest() {		
		//level: TRACE<DEBUG<INFO<WARN<ERROR
		LOGGER.trace("This is TRACE log");
		LOGGER.debug("This is DEBUG log");
		LOGGER.info("This is INFO log");
		LOGGER.warn("This is WARN log");
		LOGGER.error("This is ERROR log");
		return "This is log test";
	}
	
	/**
	 * 127.0.0.1:8080/test/config
	 * @return
	 */
	@RequestMapping("/test/config")
	@ResponseBody
	public String configInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append(port).append("--------")
			.append(name).append("--------")
			.append(age).append("--------")
			.append(desc).append("--------")
			.append(random).append("<br>");
			
		sb.append(applicationTest.getName01()).append("--------")
			.append(applicationTest.getAge01()).append("--------")
			.append(applicationTest.getDesc01()).append("--------")
			.append(applicationTest.getRandom01()).append("<br>");
			
		return sb.toString();
	}
	
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
