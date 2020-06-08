package com.hqyj.SpringBootDemo.modules.test.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.SpringBootDemo.modules.test.entity.City;
import com.hqyj.SpringBootDemo.modules.test.entity.Country;
import com.hqyj.SpringBootDemo.modules.test.service.CityService;
import com.hqyj.SpringBootDemo.modules.test.service.CountryService;
import com.hqyj.SpringBootDemo.modules.test.vo.ApplicationTest;

@Controller
@RequestMapping("/test")
public class TestController {
		
	@Value("${server.port}")
	private int port;
	@Value("${com.thornBird.name}")
	private String name;
	@Value("${com.thornBird.age}")
	private int age;
	@Value("${com.thornBird.desc}")
	private String desc;
	@Value("${com.thornBird.random}")
	private String random;
	
	@Autowired
	private ApplicationTest applicationTest;
	@Autowired
	private CityService cityService;
	@Autowired
	private CountryService countryService;
	
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	/**
	 * 127.0.0.1:8080/test/log
	 * @return
	 */
	@RequestMapping("/log")
	@ResponseBody
	public String logTest() {
		//level: TRACE<DEBUG<INFO<WARN<ERROR
		LOGGER.trace("This is TRACE log");
		LOGGER.debug("This is DEBUG log");
		LOGGER.info("This is INFO log");
		LOGGER.warn("This is WARN log");
		LOGGER.error("This is ERROR log");
		return "This is log test";
	}
	
	/**
	 * 127.0.0.1:8080/test/config
	 * @return
	 */
	@RequestMapping("/config")
	@ResponseBody
	public String configInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append(port).append("--------")
			.append(name).append("--------")
			.append(age).append("--------")
			.append(desc).append("--------")
			.append(random).append("<br>");
			
		sb.append(applicationTest.getName01()).append("--------")
			.append(applicationTest.getAge01()).append("--------")
			.append(applicationTest.getDesc01()).append("--------")
			.append(applicationTest.getRandom01()).append("<br>");
			
		return sb.toString();
	}
	
	/**
	 * 127.0.0.1:8080/test/desc
	 * 
	 * 127.0.0.1/test/desc？key=fuck
	 * @return
	 */
	@RequestMapping("/desc")	
	@ResponseBody
	//返回的是一个接口，而不是一个页面控制器
	public String testDesc(HttpServletRequest request,@RequestParam String key) {
		String key2 = request.getParameter("key");
		return "This is test module desc.02" + key + "===" + key2;
	}
	
	/**
	 * 127.0.0.1/test/index
	 * @return
	 */
	@RequestMapping("/index")
	public String indexPage(ModelMap modelMap) {
		
		int countryId = 522;
		List<City> cities = cityService.getCitiesByCountryId(countryId);
		cities = cities.stream().limit(10).collect(Collectors.toList());
		
		Country country = countryService.getCountryByCountryId(countryId);
		
		modelMap.addAttribute("thymeleafTitle","王五");
		modelMap.addAttribute("checked",true);
		modelMap.addAttribute("currentNumber","99");
		modelMap.addAttribute("changeType","checkbox");
		modelMap.addAttribute("baiduUrl","/test/log");
		modelMap.addAttribute("city",cities.get(0));
		modelMap.addAttribute("shopLogo",
				"https://static.veer.com/veer/static/resources/keyword/2020-02-19/533ed30de651499da1c463bca44b6d60.jpg");
		modelMap.addAttribute("country",country);
		modelMap.addAttribute("cities",cities);
		modelMap.addAttribute("updateCityUri","/api/city");
		
		
		modelMap.addAttribute("template","test/index");
		return "index";//indexSimple
	}
	
}
