package com.mh453Uol.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
public class ErrorMessage {
    private int statusCode;
    private String errorMessage;
	private List<KeyValuePair> errors;
	
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

	public ErrorMessage() {
		this.errors = new ArrayList<>();
	}
	
	public ErrorMessage(List<KeyValuePair> errors) {
		this.errors = errors;
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
