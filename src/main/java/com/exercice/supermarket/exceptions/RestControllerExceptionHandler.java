package com.exercice.supermarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {
	
	@ExceptionHandler(ObjectWrongFormatException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String objectErongFormatException(ObjectWrongFormatException ex) {
		return "The object is passed in a wrong format";
	}

}
