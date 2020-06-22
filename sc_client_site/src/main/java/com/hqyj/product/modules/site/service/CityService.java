package com.hqyj.product.modules.site.service;

import java.util.List;

import com.hqyj.product.modules.site.dao.CityDao;
import com.hqyj.product.modules.site.entity.City;
 
public interface CityService {
	
	List<City> getCitiesByCountryId(int countryId);
	
}
