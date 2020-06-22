package com.hqyj.product;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.hqyj.product.modules.site.service.impl.CityServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CityServiceImpl.class)
@Import(ScProductApplication.class)
public class CityServiceImplTest {
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Test
	public void getCitiesByCountryIdTest(){
		IntStream.range(0, 100).forEach(i -> {
			ServiceInstance serviceInstance = this.loadBalancerClient.choose("CLIENT-SITE");
			System.out.println(String.format("%d----%s----%d", i,serviceInstance.getHost(),serviceInstance.getPort()));
		});
	}
	
}
