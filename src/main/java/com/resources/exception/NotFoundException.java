package com.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private int status;
	private HttpStatus error;
	private String message;
	private String path;	
	
	public NotFoundException(String message, String path,HttpStatus r){
		setError(HttpStatus.NOT_FOUND);
		setMessage(message);
		setPath(path);		
		setStatus(r.value());
	}
	
}//End class
