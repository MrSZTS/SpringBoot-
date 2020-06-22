package com.hqyj.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/*
 * @EnableDiscoveryClient		适用于spring boot支持的所有注册中心
 * @EnableEurekaClient			适用于Eureka注册中心
 */
@SpringBootApplication
@EnableDiscoveryClient//适用于spring boot支持的所有注册中心
public class ScClientAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScClientAccountApplication.class, args);
	}

}
