package com.hqyj.SpringBootDemo.modules.test.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/applicationTest.properties")
@ConfigurationProperties(prefix = "com.test")
public class ApplicationTest {
	
	private String name01;
	private int age01;
	private String desc01;
	private String random01;
	
	public String getName01() {
		return name01;
	}
	public void setName01(String name01) {
		this.name01 = name01;
	}
	public int getAge01() {
		return age01;
	}
	public void setAge01(int age01) {
		this.age01 = age01;
	}
	public String getDesc01() {
		return desc01;
	}
	public void setDesc01(String desc01) {
		this.desc01 = desc01;
	}
	public String getRandom01() {
		return random01;
	}
	public void setRandom01(String random01) {
		this.random01 = random01;
	}
	
	
}
