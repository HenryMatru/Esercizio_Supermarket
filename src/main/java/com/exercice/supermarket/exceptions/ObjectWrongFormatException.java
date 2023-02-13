package com.exercice.supermarket.exceptions;

public class ObjectWrongFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectWrongFormatException(String message) {
		super(message);
	}

}
