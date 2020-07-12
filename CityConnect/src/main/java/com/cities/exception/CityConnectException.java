package com.cities.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CityConnectException extends RuntimeException {
	
	private static final long serialVersionUID = 2877468457949351160L;
	
	private String message;
	private String operation;
	private HttpStatus status;
	
	public CityConnectException(HttpStatus status) {
		super();
		this.status = status;
	}
	
	
}
