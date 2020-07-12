package com.cities.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cities.model.CityConnectResponse;
import com.cities.service.CityConnectService;
import com.cities.util.CityConnectUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/")
@Api (tags = "City Connect API details")
public class CityConnectController {
	
	private static final Logger logger = LoggerFactory.getLogger(CityConnectController.class);
	
	@Autowired
	CityConnectService service;

	@GetMapping("/connected")
	@ApiOperation (value = "This API takes an origin and a destination cities as input "
			+ "and determines if the cities are connected by road")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad request. This means either the origin and destination"
					+ " cities are not passed or an invalid value is passed")})
	public ResponseEntity<CityConnectResponse> checkCityConnection (
			@ApiParam (value = "Starting point", required = true)
				@RequestParam(value="origin") String origin,
			@ApiParam (value = "Destination", required = true)
				@RequestParam(value="destination") String destination) {
	
		logger.info("Enter checkCityConnection :: origin = " + origin 
				+ " :: destination = " + destination);
		
		LocalDateTime requestTime = LocalDateTime.now();
		
		if (CityConnectUtil.isNullorEmpty(origin) || CityConnectUtil.isNullorEmpty(destination)
				|| origin.chars().anyMatch(c -> (!Character.isLetter(c) && !Character.isSpaceChar(c)))
				|| destination.chars().anyMatch(c -> (!Character.isLetter(c) && !Character.isSpaceChar(c)))) {
			
			logger.error("checkCityConnection :: Invalid input");
			return new ResponseEntity<CityConnectResponse>(HttpStatus.BAD_REQUEST);
		}
		
		CityConnectResponse response = new CityConnectResponse();
		response.setOrigin(origin);
		response.setDestination(destination);
		response.setAreConnected(service.checkCityConnection(origin, destination));
		response.setRequestTime(requestTime);
		
		LocalDateTime responseTime = LocalDateTime.now();
		response.setResponseTime(responseTime);
		
		return new ResponseEntity<CityConnectResponse>(response, HttpStatus.OK);
		
	}
}
