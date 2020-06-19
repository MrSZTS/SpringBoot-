package com.hqyj.SpringBootDemo.modules.common.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;

@ControllerAdvice//全局的异常控制类（类似于控制器）
public class ExceptionHandlerController {
	
	@ExceptionHandler(value = AuthorizationException.class)//局部，只针对某个异常
	@ResponseBody//返回接口，要加
	public Result<String> exceptionHandlerFor304(){
		return new Result<String>(ResultStatus.FAILED.status,"you hava no permission!","/common/403");
	}
	
}
