package com.hqyj.SpringBootDemo.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.entity.City;
import com.hqyj.SpringBootDemo.modules.test.service.CityService;

@RestController
@RequestMapping("/api")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	/**
	 * 127.0.0.1/api/cities/522 ------get
	 * @param countryId
	 * @return
	 */
	@RequestMapping("/cities/{countryId}")
	public List<City> getCitiesByCountryId(@PathVariable int countryId) {
		return cityService.getCitiesByCountryId(countryId);
	}
	
	/**
	 * 127.0.0.1/api/city?cityName=Shanghai&localCityName=1111 ------get
	 * @param countryId
	 * @return
	 */
	@RequestMapping("/city")
	public City getCityByName(@RequestParam String cityName,@RequestParam(required = false) String localCityName) {
		return cityService.getCityByName(cityName, localCityName);
	}
	
	/**
	 * 127.0.0.1/api/cities?currentPage=1&pageSize=5&countryId=522
	 * @param currentPage
	 * @param pageSize
	 * @param countryId
	 * @return
	 */
	@RequestMapping("/cities")
	public PageInfo<City> getCitiesPage(@RequestParam int currentPage,@RequestParam  int pageSize,
											@RequestParam  int countryId) {
		return cityService.getCitiesPage(currentPage, pageSize, countryId);
	}
	
	/**
	 * 127.0.0.1/api/cities
	 * @param serarchVo
	 * @return
	 */
	@PostMapping(value = "cities",consumes = "application/json")
	//@RequestMapping(value = "/cities",method = RequestMethod.POST,consumes = "application/json")
	public PageInfo<City> getCitiesBySearchVo(@RequestBody SearchVo serarchVo) {
		return cityService.getCitiesBySearchVo(serarchVo);
	}
	
}
