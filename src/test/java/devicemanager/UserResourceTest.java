package devicemanager;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import com.mh453Uol.domain.User;
import com.mh453Uol.exceptions.AlreadyExistExceptionMapper;
import com.mh453Uol.resources.UserResource;

public class UserResourceTest extends JerseyTest {
	
	@Override
	protected Application configure() {
		return new ResourceConfig(UserResource.class)
				//register your exception mapper to simulate real environment!
		        .register(new AlreadyExistExceptionMapper());
	}
	
	@Test
	public void AddUser_WhereEmailIsUnique_Returns201Created() {
		Response response = target("/users/register").request()
				.post(Entity.entity(new User("majid@google.com", "noisy"), MediaType.APPLICATION_JSON));

		Assert.assertEquals("response is 201 created", Status.CREATED.getStatusCode(), response.getStatus());
	}

	@Test
	public void AddUser_WhereEmailAlreadyExists_Returns409() {
		Response response1 = target("/users/register").request()
				.post(Entity.entity(new User("tom@google.com", "noisy"), MediaType.APPLICATION_JSON));
		
		Response response2 = target("/users/register").request()
				.post(Entity.entity(new User("tom@google.com","noisy"),MediaType.APPLICATION_JSON));
		
		Assert.assertEquals("response is 409 conflict", Status.CONFLICT.getStatusCode(), response2.getStatus());
	}
}
