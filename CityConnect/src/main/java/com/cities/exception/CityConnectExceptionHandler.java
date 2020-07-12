package com.cities.exception;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cities.util.CityConnectUtil;

@ControllerAdvice
public class CityConnectExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String GENERIC_EXCEPTION_MSG = "An error has occurred";
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleGenericException(Exception exception, WebRequest request) 
																		throws Exception {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse ();
		exceptionResponse.setTimestamp(LocalDateTime.now());
		exceptionResponse.setMessage(GENERIC_EXCEPTION_MSG);
		
		ResponseEntity<ExceptionResponse> responseEntity = 
				new ResponseEntity<ExceptionResponse> (exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return responseEntity;
		
	}
	
	@ExceptionHandler(CityConnectException.class)
	public final ResponseEntity<ExceptionResponse> handleContractorManagementException(CityConnectException exception, 
																			WebRequest request) throws Exception {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse ();
		exceptionResponse.setTimestamp(LocalDateTime.now());
		exceptionResponse.setMessage
			(CityConnectUtil.isNullorEmpty(exception.getMessage()) ? GENERIC_EXCEPTION_MSG : exception.getMessage());
		exceptionResponse.setOperation(exception.getOperation());
		
		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<ExceptionResponse> (exceptionResponse, 
				Optional.ofNullable(exception.getStatus()).isPresent() ? exception.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR);
		
		return responseEntity;
	}
		
	
}
