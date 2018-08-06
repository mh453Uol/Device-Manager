package com.mh453Uol.filters;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Provider
public class LoggingRequestFilter implements ContainerRequestFilter {
	private Logger logger = LogManager.getLogger(LoggingRequestFilter.class);
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		//System.out.println("filter");
	}
}
