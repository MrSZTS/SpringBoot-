package com.hqyj.SpringBootDemo.interceptor;
/*
 * 拦截器配置类
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class UrlInterceptor implements HandlerInterceptor {

	private final static Logger LOGGER = LoggerFactory.getLogger(UrlInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub  之前
		LOGGER.debug("========== pre controller ==========");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.debug("========== post controller ==========");
		//如果没有页面要返回或重定向的路径则直接返回
		if(modelAndView == null || modelAndView.getViewName().startsWith("redirect")) {
			return;
		}
		
		//获取路径
		//http://127.0.0.1/test/index
		//request.getServletPath()			/test/index
		String path = request.getServletPath();
		//查看ModelMap中是否含有手动设置的template路径
		String template = (String) modelAndView.getModelMap().get("template");
		//如果template是空，就添加template
		if (StringUtils.isBlank(template)) {
			if(path.startsWith("/")) {
				//substring 切割字符串，此句中是将/切割掉
				//public String substring(int beginIndex,int endIndex)
				//返回一个新字符串bai，它是此字符串的一个子du字符串。该zhi子字符串从指定的 beginIndex 处开始，直到索引dao endIndex - 1 处的字符。
				path = path.substring(1);
			}
			modelAndView.getModelMap().addAttribute("template",path.toLowerCase());
		}
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub	之后
		LOGGER.debug("========== after controller ==========");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
	
}
