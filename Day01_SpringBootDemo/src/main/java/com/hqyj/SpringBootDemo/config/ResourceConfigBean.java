package com.hqyj.SpringBootDemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/application.properties")
public class ResourceConfigBean {
	
	@Value("${spring.source.path}")
	private String resourcePath;
	@Value("${spring.source.path.pattern}")
	private String resourcePathPattern; 
	@Value("${spring.source.folder.windows}")
	private String localPathForWindows;
	@Value("${spring.source.folder.linux}")
	private String localPathForLinux;
	
	
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	public String getResourcePathPattern() {
		return resourcePathPattern;
	}
	public void setResourcePathPattern(String resourcePathPattern) {
		this.resourcePathPattern = resourcePathPattern;
	}
	public String getLocalPathForWindows() {
		return localPathForWindows;
	}
	public void setLocalPathForWindows(String localPathForWindows) {
		this.localPathForWindows = localPathForWindows;
	}
	public String getLocalPathForLinux() {
		return localPathForLinux;
	}
	public void setLocalPathForLinux(String localPathForLinux) {
		this.localPathForLinux = localPathForLinux;
	}

	
	
}
