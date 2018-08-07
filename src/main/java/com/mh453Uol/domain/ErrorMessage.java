package com.mh453Uol.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
public class ErrorMessage {
    private int statusCode;
    private String errorMessage;
	private List<KeyValuePair> errors;
	
	public ErrorMessage() {
		this.errors = new ArrayList<>();
	}
	
	public ErrorMessage(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.errors = new ArrayList<KeyValuePair>();
	} 
	
	public ErrorMessage(int statusCode, String errorMessage, List<KeyValuePair> errors) {
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.errors = errors;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void addError(KeyValuePair keyValuePair) {
		this.errors.add(keyValuePair);
	}

	public List<KeyValuePair> getErrors() {
		return errors;
	}

	public void setErrors(List<KeyValuePair> errors) {
		this.errors = errors;
	}
}
