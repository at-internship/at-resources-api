package com.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private int status;
    private HttpStatus error;
    private String message;
    private String path;

    public BadRequest(String message, String path, HttpStatus r){
        setError(HttpStatus.BAD_REQUEST);
        setMessage(message);
        setPath(path);
        setStatus(r.value());
    }
}
