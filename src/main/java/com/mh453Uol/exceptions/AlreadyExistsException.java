package com.mh453Uol.exceptions;

public class AlreadyExistsException extends Exception {
	private int statusCode;
	private String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyExistsException(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
