package devicemanager;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import com.mh453Uol.domain.Device;
import com.mh453Uol.resources.DevicesResource;

public class DeviceResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(DevicesResource.class);
	}

	@Test
	public void AddDevice_WhereIpAddressAndHostAreDefined_Returns201() {
		Device device = new Device("ipad", "192.160.177.129", "GITHUB");
		Response response = target("/devices").request().post(Entity.entity(device, MediaType.APPLICATION_JSON));
		assertEquals("should return 201",201,response.getStatus());
	}
	
	@Test 
	public void AddDevice_WhereIpAddressAndHostAreNotDefined_Returns201() {
		Device device = new Device();
		device.setName("ipad");
		
		Response response = target("/devices").request().post(Entity.entity(device, MediaType.APPLICATION_JSON));
		assertEquals("should return 201",201,response.getStatus());
	}
	
	
	@Test
	public void AddDevice_WhereThereIsValidationErrors_Returns400() {
		Response response = target("/devices").request().post(Entity.entity(new Device(), MediaType.APPLICATION_JSON));
		assertEquals("should return 400",400,response.getStatus());
	}
	
	@Test
	public void AddDevice_WhichCurrentlyExists_Returns400() {
		//already exist 
		Device device = new Device("mac", "100.92.54.176", "mac book pro");
		Response response = target("/devices").request().post(Entity.entity(device, MediaType.APPLICATION_JSON));
		assertEquals("should return 400",400,response.getStatus());		
	}
	
	@Test
	public void GetDevices_Returns200() {
		Response response = target("/devices").request().get(); 
		assertEquals("should return 200", 200,response.getStatus());
	}
	
	@Test
	public void EditDevice_whichExists_Returns200() {
		Device device = new Device("NEW mac", "100.92.54.176", "NEW mac book pro");
		Response response = target("/devices/"+"100.92.54.176").request().put(Entity.entity(device, MediaType.APPLICATION_JSON));
		assertEquals("should return 200 since device exists so it can be updated", 200,response.getStatus());
	}
	
	@Test
	public void EditDevice_WhichDoesntExists_Returns404() {
		Device device = new Device("NEW mac", "", "NEW mac book pro");
		Response response = target("/devices/"+"255.255.255.255").request().put(Entity.entity(device, MediaType.APPLICATION_JSON));
		assertEquals("should return 404 since device doesnt exists so it cant be updated", 404,response.getStatus());
	}
	
	
	@Test
	public void EditDevice_WhereIpAddressAndHostNameAreNotDefined_Returns400() {
		Device device = new Device();
		device.setName("FOO BAR");
		
		Response response = target("/devices/"+"100.92.54.176").request().put(Entity.entity(device, MediaType.APPLICATION_JSON));
		assertEquals("should return 400 since ip details are not specified", 400,response.getStatus());
	}
	
}
