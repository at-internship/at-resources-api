package com.resources.model;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {

	private Date timestamp;
	private int status;
	private String error;
	private String message;
	private String path;

	public ApiError() {
		this.timestamp = new Date();
	}

	public ApiError(HttpStatus error) {
		this();
		this.status = error.value();
		this.error = error.getReasonPhrase();
	}

	public ApiError(HttpStatus error, String message) {
		this();
		this.status = error.value();
		this.error = error.getReasonPhrase();
		this.message = message;
	}

	public ApiError(HttpStatus error, int status, String message, String path) {
		this();
		this.status = error.value();
		this.error = error.getReasonPhrase();
		this.message = message;
		this.path = path;
	}
}