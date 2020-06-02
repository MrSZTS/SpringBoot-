package com.hqyj.SpringBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 
 * 启动类
 * 
 * 为什么自定义的业务层代码要放到基础包里面？因为基础包启动类会扫描自己写的代码，放到基础包的外层就无法扫描到了，IOC就无法注册相应的bean
 * 
 * @author sxm
 *
 */
@SpringBootApplication
public class Day01SpringBootDemoApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		//把启动类（类模板）加入到配置中
		return builder.sources(Day01SpringBootDemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Day01SpringBootDemoApplication.class, args);
	}

}
