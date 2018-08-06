package com.mh453Uol.filters;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
@Authorized
public class BasicAuthFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// header contains the following {Authorization "Basic Username:Password"} where
		// <Username:Password> is encoded in base64

		List<String> authorizations = requestContext.getHeaders().get("Authorization");

		if (authorizations != null && authorizations.size() > 0) {
			// we assume first header is the basic authentication credentials
			String base64EncodedToken = authorizations.get(0);
			// strip out basic part since we only want username:password sections
			base64EncodedToken = base64EncodedToken.replace("Basic ", "");
			String decodedToken = Base64.decodeAsString(base64EncodedToken);

			String[] credentials = decodedToken.split(":");

			if (credentials.length == 2) {
				final String username = credentials[0];
				final String password = credentials[1];

				requestContext.setSecurityContext(getSecurityContext(requestContext, username));

				return;
			}

		}

		// if we reach here the headers are incorrect
		Response unAuthorized = Response.status(Status.UNAUTHORIZED).entity("User not authorized").build();

		requestContext.abortWith(unAuthorized);
	}

	public SecurityContext getSecurityContext(ContainerRequestContext requestContext, String username) {
		final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
		return new SecurityContext() {
			//getting the username in resources classes
			@Override
			public Principal getUserPrincipal() {
				return () -> username;
			}

			@Override
			public boolean isUserInRole(String role) {
				return true;
			}

			@Override
			public boolean isSecure() {
				return currentSecurityContext.isSecure();
			}

			@Override
			public String getAuthenticationScheme() {
				return "Basic ";
			}
		};
	}
}
