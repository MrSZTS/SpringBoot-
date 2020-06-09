package com.hqyj.SpringBootDemo.aspect;
/*
 * 1-面向切面编程，可以设置多个切点，然后再此类中设置多个方法，并与切点一一绑定即可
 * 2-设置通知机制（before、after、around、异常的切点等）
 * 3-业务逻辑：参数可用的是JoinPoint、ProceedingJoinPoint
 * 
 */

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hqyj.SpringBootDemo.filter.ParameterFilter;

@Aspect
@Component
public class ControllerAspect {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ParameterFilter.class);
	/**
	 * 关联在方法上的切点
	 * 第一个*代表返回类型不限
	 * 第二个*代表module下所有子包
	 * 第三个*代表所有类
	 * 第四个*代表所有方法
	 * (..) 代表参数不限
	 * Order 代表优先级，数字越小优先级越高
	 */
	//设置切点，定义具体的接入点
	@Pointcut("execution(public * com.hqyj.SpringBootDemo.modules.*.controller.*.*(..))")
	//order代表优先级，数字越小优先级越高
	@Order(1)
	public void controllerPointCut() {}
	
	@Before(value = "com.hqyj.SpringBootDemo.aspect.ControllerAspect.controllerPointCut()")
	public void beforeController(JoinPoint joinPoint) {
		LOGGER.debug("++++++++++ BEFORE CONTROLLER ++++++++++");
		
		ServletRequestAttributes attributes = 
				(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		LOGGER.debug("请求来源：" + request.getRemoteAddr());
		LOGGER.debug("请求URL：" + request.getRequestURL().toString());
		LOGGER.debug("请求方式：" + request.getMethod());
		LOGGER.debug("响应方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + 
				joinPoint.getSignature().getName());
		LOGGER.debug("请求参数：" + Arrays.toString(joinPoint.getArgs()));
	
	}
	
	@After(value = "com.hqyj.SpringBootDemo.aspect.ControllerAspect.controllerPointCut()")
	public void afterController() {
		LOGGER.debug("++++++++++ AFTER CONTROLLER ++++++++++");
	}
	
	@Around(value = "com.hqyj.SpringBootDemo.aspect.ControllerAspect.controllerPointCut()")
	public Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		LOGGER.debug("++++++++++ AROUND CONTROLLER ++++++++++");
		return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
	}
	
	
}
