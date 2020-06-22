package com.hqyj.product.modules.site.service.impl;

import java.nio.file.OpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.product.modules.site.dao.CityDao;
import com.hqyj.product.modules.site.entity.City;
import com.hqyj.product.modules.site.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;
	
	@Override
	public List<City> getCitiesByCountryId(int countryId) {
		
		return Optional.ofNullable(cityDao.getCitiesByCountryId(countryId)).orElse(Collections.emptyList());
	}

}
