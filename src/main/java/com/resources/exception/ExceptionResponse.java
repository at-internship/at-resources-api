package com.resources.exception;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
	
	private Date timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	
	public ExceptionResponse(Date timestamp,int status,HttpStatus error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error.getReasonPhrase();
		this.message = message;
		this.path = path;
	}

}//End class