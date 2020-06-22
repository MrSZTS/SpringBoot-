package com.hqyj.product.modules.site.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hqyj.product.modules.site.entity.City;

@Mapper
public interface CityDao {
	
	@Select("select * from m_city where country_id=#{countryId}")
	List<City> getCitiesByCountryId(int countryId);
	
}
