package com.mh453Uol.filters;

import java.io.IOException;

import javax.ws.rs.container.*;
import javax.ws.rs.ext.Provider;

@Provider
public class PoweredByResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		responseContext.getHeaders().add("X-POWERED-BY", "MAJID");
	}

}
