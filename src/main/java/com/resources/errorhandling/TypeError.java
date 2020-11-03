package com.resources.errorhandling;

import org.springframework.http.HttpStatus;

public class TypeError extends RuntimeException {

	private static final long serialVersionUID = 1L;
	static HttpStatus newTypeError;
	static int httpError;
	static String httpNameError;
	static String message;
	static String path;

	public TypeError(HttpStatus notFound, int value, String idnotfound404, String pathParameter) {
		newTypeError = notFound;
		httpError = value;
//		httpNameError = typeError.name();
		message = idnotfound404;
		path = pathParameter;
	}
	
}