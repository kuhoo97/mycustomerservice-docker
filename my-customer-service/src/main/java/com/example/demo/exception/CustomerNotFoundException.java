package com.example.demo.exception;

public class CustomerNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8612131917078021177L;
	private String message;
	public CustomerNotFoundException(String message) {
		
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	
}
