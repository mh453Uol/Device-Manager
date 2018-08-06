package com.mh453Uol.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.glassfish.hk2.utilities.reflection.Logger;

import com.mh453Uol.domain.ErrorMessage;

//This is a global error handler it handles both errors (JVM Error) and exceptions (System Exceptions)
@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable e) {
		Response.StatusType type = getStatusType(e);

		if (type == Response.Status.INTERNAL_SERVER_ERROR) {
			LogManager.getLogger(ApplicationExceptionMapper.class).error("INTERNAL SERVER ERROR (500)", e);
		}

		ErrorMessage error = new ErrorMessage();
		error.setStatusCode(type.getStatusCode());
		error.setErrorMessage(type.getReasonPhrase());

		return Response.status(error.getStatusCode()).entity(error).type(MediaType.APPLICATION_JSON).build();
	}

	private Response.StatusType getStatusType(Throwable ex) {
		if (ex instanceof WebApplicationException) {
			return ((WebApplicationException) ex).getResponse().getStatusInfo();
		} else {
			return Response.Status.INTERNAL_SERVER_ERROR;
		}
	}
}