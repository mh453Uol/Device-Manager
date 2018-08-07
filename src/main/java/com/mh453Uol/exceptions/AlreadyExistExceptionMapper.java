package com.mh453Uol.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.mh453Uol.domain.ErrorMessage;

@Provider
public class AlreadyExistExceptionMapper implements ExceptionMapper<AlreadyExistsException> {

	@Override
	public Response toResponse(AlreadyExistsException exception) {
		return Response.status(exception.getStatusCode())
				.entity(new ErrorMessage(exception.getStatusCode(),exception.getMessage()))
				.build();
	}
}
