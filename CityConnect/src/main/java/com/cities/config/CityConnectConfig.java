package com.cities.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cities.util.CityConnectUtil;


@Component
public class CityConnectConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(CityConnectConfig.class);
	
	private static Map<String, List<String>> cities;
	
	@Value("${cities.repo}")
	private String citiesRepo;

	/**
	 * Load all city pairs from the text file and store in the HashMap on startup
	 */
	@PostConstruct
	public void loadCities () {
		
		if (!CityConnectUtil.isNullOrEmpty(cities))
			return;
		
		try (Stream<String> stream = Files.lines(Paths.get(citiesRepo))) {

			cities = new HashMap<String, List<String>>();
			
			stream.map(line -> line.split(","))
				.filter(Objects::nonNull)
				.filter(line -> line.length==2)
				.forEach(line -> {
					String city1 = line[0];
					String city2 = line[1];
					
					if (!CityConnectUtil.isNullorEmpty(city1) 
							&& city1.chars().allMatch(c -> (Character.isLetter(c) || Character.isSpaceChar(c)))
							&& !CityConnectUtil.isNullorEmpty(city2)
							&& city2.chars().allMatch(c -> (Character.isLetter(c) || Character.isSpaceChar(c)))) {
					
						logger.debug("loadCities :: city1 = " + city1 + " :: city2 = " + city2);
						
						cities.computeIfAbsent(city1.trim().toUpperCase(), k -> new ArrayList<>()).add(city2.trim().toUpperCase());
						cities.computeIfAbsent(city2.trim().toUpperCase(), k -> new ArrayList<>()).add(city1.trim().toUpperCase());
					}
					
			});
			
			logger.info("loadCities :: Loaded cities = " + cities);
	
		} catch (IOException e) {
			logger.error("loadCities :: Exception in loading cities: " + e);
		}

	}

	public static Map<String, List<String>> getCities() {
		return cities;
	}

}
