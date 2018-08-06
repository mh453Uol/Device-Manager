package com.mh453Uol.exceptions;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.validator.internal.engine.path.PathImpl;

import com.mh453Uol.domain.ErrorMessage;
import com.mh453Uol.domain.KeyValuePair;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<javax.validation.ValidationException> {

    @Override
    public Response toResponse(javax.validation.ValidationException e) {
    	ErrorMessage error = new ErrorMessage();
    	error.setStatusCode(400);
    	error.setErrorMessage("400 Bad Request");
    	
        for (ConstraintViolation<?> cv : ((ConstraintViolationException) e).getConstraintViolations()) {
        	String propertyOnError = ((PathImpl)cv.getPropertyPath()).getLeafNode().getName();
            error.addError(new KeyValuePair(propertyOnError,cv.getMessage()));
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(error)
                .build();
    }
}