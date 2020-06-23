package com.hqyj.account.modules.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hqyj.account.modules.account.entity.City;
import com.hqyj.account.modules.account.service.SiteFeignClient;

@Component
public class SiteFeignClientFallBack implements SiteFeignClient{

	@Override
	public List<City> getCitiesByCountryId(int countryId) {

		return new ArrayList<City>();
	}

}
