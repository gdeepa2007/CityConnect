package com.cities.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@ToString (includeFieldNames = true)
public class ExceptionResponse {
	
	private LocalDateTime timestamp;
	private String message;
	private String operation;

}
