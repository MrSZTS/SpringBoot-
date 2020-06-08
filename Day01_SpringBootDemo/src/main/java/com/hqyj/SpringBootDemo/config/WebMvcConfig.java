package com.hqyj.SpringBootDemo.config;

import javax.servlet.annotation.WebFilter;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hqyj.SpringBootDemo.filter.ParameterFilter;
import com.hqyj.SpringBootDemo.interceptor.UrlInterceptor;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer{

	@Value("${server.http.port}")
	private int httpPort;
	
	@Autowired
	private UrlInterceptor urlInterceptor;
	
	@Bean
	public Connector connector() {
		Connector connector = new Connector();
		connector.setScheme("http");
		connector.setPort(httpPort);
		return connector;
	}
	
	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.addAdditionalTomcatConnectors(connector());
		return factory;
	}
	
	//过滤器（过滤url）
	@Bean
	public FilterRegistrationBean<ParameterFilter> filter(){
		FilterRegistrationBean<ParameterFilter> register = new FilterRegistrationBean<ParameterFilter>();
		register.setFilter(new ParameterFilter());
		//register.addUrlPatterns();与过滤器类中的注释@WebFilter中的  urlPatterns = "/**"  一样
		return register;
	}

	//拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {		
		registry.addInterceptor(urlInterceptor).addPathPatterns("/**");
				
	}
	
	
}
