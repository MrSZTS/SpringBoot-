package com.hqyj.SpringBootDemo.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//shiro的配置类
@Configuration//相当于beans
public class ShiroConfig {
	
	@Autowired
	private MyRealm myRealm;
	
	@Bean//securityManager
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm);
		return securityManager;
	}
	
	@Bean//shiroFileterFactoryBean
	public ShiroFilterFactoryBean shiroFileterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/account/login");
		// 登录成功后要跳转的连接
		shiroFilterFactoryBean.setSuccessUrl("/common/dashboard");
		//没有权限默认跳转的页面，登录的用户访问了没有被授权的资源自动跳转到的页面。
		//shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
		return shiroFilterFactoryBean;
	}
	

}
