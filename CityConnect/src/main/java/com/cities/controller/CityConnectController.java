package com.cities.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cities.model.CityConnectResponse;

@RestController
@RequestMapping("/")
public class CityConnectController {

	@GetMapping("/connected")
	public ResponseEntity<CityConnectResponse> checkCityConnection (
			@RequestParam(value="origin") String origin,
			@RequestParam(value="destination") String destination) {
	
		CityConnectResponse response = new CityConnectResponse();
		response.setConnected(true);
		
		return new ResponseEntity<CityConnectResponse>(response, HttpStatus.OK);
		
	}
}
