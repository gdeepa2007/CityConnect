package com.cities.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@ToString (includeFieldNames = true)
@ApiModel (description = "Response from City Connect APIs")
public class CityConnectResponse {

	@ApiModelProperty (notes = "True or false depending on whether the cities are connected", position = 3)
	private boolean areConnected;
	
	@ApiModelProperty (notes = "Origin passed in the input", position = 1)
	private String origin;
	
	@ApiModelProperty (notes = "Destination passed in the input", position = 2)
	private String destination;
	
	@ApiModelProperty (notes = "Time at which API received the request", position = 4)
	private LocalDateTime requestTime;
	
	@ApiModelProperty (notes = "Time at which API returned the response", position = 5)
	private LocalDateTime responseTime;
}
