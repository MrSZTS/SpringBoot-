package com.hqyj.gateway.filter;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpContext;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class MyZuulFilter extends ZuulFilter {

	//是否执行该过滤器
	@Override
	public boolean shouldFilter() {
		return true;
	}

	//过滤器业务逻辑
	@Override
	public Object run() throws ZuulException {
		//拿到请求的查询参数
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		String aaa = request.getParameter("aaa");
		if(aaa == null || aaa == "") {
			//过滤该请求，不对其进行路由
			requestContext.setSendZuulResponse(false);
			//设置响应状态码
			requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
			//设置响应体
			requestContext.setResponseBody("aaa is empty!");
		}
		return null;
	}

	//过滤器类型
	@Override
	public String filterType() {
		return "pre";
	}

	//过滤器优先级，0代表优先级最高
	@Override
	public int filterOrder() {
		return 0;
	}

}
