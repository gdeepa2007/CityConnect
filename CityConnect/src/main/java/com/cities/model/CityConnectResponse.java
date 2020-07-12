package com.cities.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@ToString (includeFieldNames = true)
public class CityConnectResponse {

	private boolean isConnected;
	
	private String origin;
	
	private String destination;
	
	private LocalDateTime requestTime;
	
	private LocalDateTime responseTime;
}
