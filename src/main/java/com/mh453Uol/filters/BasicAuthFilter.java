package com.mh453Uol.filters;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import com.mh453Uol.domain.ErrorMessage;
import com.mh453Uol.domain.User;
import com.mh453Uol.services.UserService;

@Provider
@Authorized
public class BasicAuthFilter implements ContainerRequestFilter {
	private UserService userService = new UserService();
	
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
				final String email = credentials[0];
				final String password = credentials[1];

				if(!email.isEmpty() && !password.isEmpty()) {
					User user = userService.validateCredientials(email, password);
					
					if(user != null) {
						requestContext.setSecurityContext(getSecurityContext(requestContext, email));
						return;
					}
				}
			}
		}

		// if we reach here the headers are incorrect
		Response unAuthorized = Response.status(Status.UNAUTHORIZED)
					.type(MediaType.APPLICATION_JSON)
					.entity(new ErrorMessage(401,"User not authorized"))
					.build();

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
