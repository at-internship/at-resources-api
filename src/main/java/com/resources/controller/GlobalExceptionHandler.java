package com.resources.controller;

import java.util.Date;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.resources.exception.ExceptionResponse;
import com.resources.exception.NotFoundException;
import com.resources.domain.ApiError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex,
			HttpHeaders headers,
			HttpStatus status, 
			WebRequest request) {
		
		String path = request.getDescription(false).substring(4);
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, 400, "Global error.", path));
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
	}

	@ExceptionHandler
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException e) {

		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(
						new Date(),
						e.getStatus(),
						HttpStatus.BAD_REQUEST,
						e.getMessage(),
						e.getPath());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
}//End class
