package com.hqyj.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
/*
 * @EnableDiscoveryClient		适用于spring boot支持的所有注册中心
 * @EnableEurekaClient			适用于Eureka注册中心
 */
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
@EnableDiscoveryClient//适用于spring boot支持的所有注册中心
public class ScClientAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScClientAccountApplication.class, args);
	}
	
	//引入了restTemplate
	@Bean
	@LoadBalanced//负载均衡
	public RestTemplate respTemplate() {
		return new RestTemplate();
	}

}
