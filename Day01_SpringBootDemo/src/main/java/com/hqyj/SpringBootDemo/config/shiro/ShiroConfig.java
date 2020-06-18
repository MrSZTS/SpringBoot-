package com.hqyj.SpringBootDemo.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

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
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("/static/**", "anon");
		map.put("/build/**", "anon");
		map.put("/images/**", "anon");
		//map.put("/shopping/**", "anon");
		map.put("/vendors/**", "anon");
		map.put("/account/login", "anon");
		map.put("/account/register", "anon");
		map.put("/api/login", "anon");
		map.put("/api/user", "anon");
		map.put("/test/**", "anon");
		
		// 如果使用“记住我功能”，则采用user规则，如果必须要用户登录，则采用authc规则
		map.put("/**", "authc");
		//	map.put("/pay/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		
		return shiroFilterFactoryBean;
	}
	
	/**
	 * --自动代理类，支持Shiro的注解
	 */
	@Bean
	@DependsOn({"lifecycleBeanPostProcessor"})
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

    /**
     * --开启Shiro的注解
     */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

}
