package com.mh453Uol.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mh453Uol.domain.User;
import com.mh453Uol.exceptions.AlreadyExistsException;
import com.mh453Uol.services.UserService;

@Path("users")
public class UserResource {
	private UserService userService = new UserService();
	
	@Path("register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(@Valid User user) throws AlreadyExistsException {
		if(userService.userExists(user.getEmail())) {
			throw new AlreadyExistsException(409, "User already exist with email " + user.getEmail());
		}
		userService.addUser(user);
		
		return Response.status(Status.CREATED).build();
	}

	
}
