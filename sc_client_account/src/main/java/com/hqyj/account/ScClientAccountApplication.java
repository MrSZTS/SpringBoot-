package com.hqyj.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
/*
 * @EnableDiscoveryClient		适用于spring boot支持的所有注册中心
 * @EnableEurekaClient			适用于Eureka注册中心
 */
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
@EnableDiscoveryClient//适用于spring boot支持的所有注册中心
@EnableHystrix//在消费者服务中添加容错保护
@EnableFeignClients//启动类添加 @EnableFeignClients 注解，Spring 启动后会扫描标注了 @FeignClient 的接口，然后生成代理类；
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
