package com.hqyj.product.modules.site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.product.modules.site.dao.CityDao;
import com.hqyj.product.modules.site.entity.City;
import com.hqyj.product.modules.site.service.CityService;

@RestController//这个类全部以借口的形式返回出去
@RequestMapping("/api")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	/*
	 * 生产者的配置端口：8761
	 * http://127.0.0.1:8761/api/cities/522
	 */
	@RequestMapping("/cities/{countryId}")
	public List<City> getCitiesByCountryId(@PathVariable int countryId) {
		return cityService.getCitiesByCountryId(countryId);
	}
	
}
