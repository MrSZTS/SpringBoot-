package com.hqyj.SpringBootDemo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 *	/**		多级路径
 *	/* 		单级路径
 */
@WebFilter(filterName = "ParameterFilter",urlPatterns = "/**")
public class ParameterFilter implements Filter{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ParameterFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("ParameterFilter init.------------------");
	}

	@Override
	public void destroy() {
		LOGGER.debug("ParameterFilter destroy.------------------");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		LOGGER.debug("ParameterFilter do Filter.------------------");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//Map<String, String[]> maps = httpRequest.getParameterMap();
		//maps.put("kasuo", new String[] {""});		maps在servlet中是被锁住的，对其进行操作，会报错
		
		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {

			// 过滤掉getParameter方式下获取url中的值
			@Override
			public String getParameter(String name) {
				String value = httpRequest.getParameter(name);
				if (StringUtils.isNotBlank(value) && value.contains("fuck")) {
					return value.replace("fuck", "****");
				}
				
				return super.getParameter(name);
			}

			//过滤掉@RequestParam方式下获取url中的值
			@Override
			public String[] getParameterValues(String name) {
				String[] values = httpRequest.getParameterValues(name);
				
				for(int i = 0;i < values.length;i++) { 
					String temp = values[i];
					if (StringUtils.isNotBlank(temp) && temp.contains("fuck")) {
						values[i] = temp.replaceAll("fuck", "****");
					}
				}
				
				return values;
			}			
			
		};
		
		chain.doFilter(wrapper, response);
		
	}



}
