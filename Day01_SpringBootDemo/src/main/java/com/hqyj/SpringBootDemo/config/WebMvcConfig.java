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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
	@Autowired
	private ResourceConfigBean resourceConfigBean;
	
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

	//添加静态资源控制器（加入本地资源文件夹）
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//本地使用**资源文件夹，实际去**路径寻找文件夹。	必须要加file：  代表是本地的		**表示该资源下的所有
		//registry.addResourceHandler("/wenjianUpload/**").addResourceLocations("file:D:/wenjianUpload/");
		
		String systemName = System.getProperty("os.name");
		if (systemName.toLowerCase().startsWith("win")) {
			registry.addResourceHandler(resourceConfigBean.getResourcePath())
						.addResourceLocations("file:" + resourceConfigBean.getLocalPathForWindows());
		}else {
			registry.addResourceHandler(resourceConfigBean.getResourcePath())
						.addResourceLocations("file:" + resourceConfigBean.getLocalPathForLinux());
		}
		
	}
	
	
}
