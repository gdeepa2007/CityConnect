package com.cities.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cities.config.CityConnectConfig;
import com.cities.util.CityConnectUtil;

@Service
public class CityConnectService {
	
	private static final Logger logger = LoggerFactory.getLogger(CityConnectService.class);
	
	public boolean checkCityConnection (String origin, String destination) {
		
		logger.debug("Enter checkCityConnection :: origin = " + origin + " :: destination = " + destination);
		
		boolean isPresent;
		
		if (CityConnectUtil.isNullorEmpty(origin) || CityConnectUtil.isNullorEmpty(destination)
				|| CityConnectUtil.isNullOrEmpty(getAllCities())) {
			isPresent = false;
		
		} else {
			isPresent = origin.equalsIgnoreCase(destination) 
					? true
					: Optional.ofNullable(this.getAllCities().get(origin.trim().toUpperCase()))
							.map(val -> val.stream().anyMatch(v -> v.equalsIgnoreCase(destination.trim().toUpperCase())))
							.orElse(false);
		}
		
		logger.debug("checkCityConnection :: isPresent = " + isPresent);
		
		return isPresent;
	}
	
	public Map<String, List<String>> getAllCities () {
		return CityConnectConfig.getCities();
	}
		
}
