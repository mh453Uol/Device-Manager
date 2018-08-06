package com.mh453Uol.resources;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.ws.rs.core.SecurityContext;

import com.mh453Uol.domain.Device;
import com.mh453Uol.domain.ErrorMessage;
import com.mh453Uol.domain.KeyValuePair;
import com.mh453Uol.filters.Authorized;
import com.mh453Uol.services.DeviceService;
import com.mh453Uol.services.IpService;
import com.mh453Uol.utilities.networking.Ping;
import com.mh453Uol.utilities.networking.SystemProcessExecutor;

@Path("devices")
public class DevicesResource {
	private DeviceService deviceService;

	@Context
	SecurityContext securityContext;

	public DevicesResource() {
		deviceService = new DeviceService();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addDevice(@Valid Device device) {

		if (!device.ipDetailsSet()) {
			// get ip details
			InetAddress ipInfo = IpService.getIpAddress();

			if (ipInfo.getHostAddress() != null || ipInfo.getHostAddress() != null) {
				device.setIpAddress(ipInfo.getHostAddress());
				device.setHostName(ipInfo.getHostName());
			} else {
				// return error since we cant get the details from the network
				ErrorMessage errorMessage = new ErrorMessage(400, "Bad Request",
						Arrays.asList(
								new KeyValuePair("ipAddress", "ipAddress was resolved to " + device.getIpAddress()),
								new KeyValuePair("host", "host was resolved to " + device.getHostName())));

				return Response.status(Status.BAD_REQUEST).entity(errorMessage).type(MediaType.APPLICATION_JSON)
						.build();
			}
		}

		if (deviceService.deviceExists(device.getIpAddress())) {
			// our unique id is the ip address return error since devices are meant to have
			// unique ips
			ErrorMessage errorMessage = new ErrorMessage(400, "Bad Request", Arrays.asList(new KeyValuePair("ipAddress",
					"A device with the ip address: " + device.getIpAddress() + " already exists")));

			return Response.status(Status.BAD_REQUEST).entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
		}

		deviceService.addDevice(device);

		return Response.status(Status.CREATED).type(MediaType.APPLICATION_JSON).entity(device).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Authorized
	public Response GetAllDevices() throws Exception {
		return Response.ok(deviceService.getAllDevices()).build();
	}

	@GET
	@Path("/{ipAddress}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDeviceByIpAddress(@PathParam("ipAddress") String ipAddress) {
		System.out.println(securityContext.getUserPrincipal().getName());

		Device device = deviceService.getDeviceById(ipAddress);
		if (device == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(device).build();
	}

	@PUT
	@Path("/{ipAddress}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editDevice(@Valid Device updatedDevice, @PathParam("ipAddress") String ipAddress) {
		Device device = deviceService.getDeviceById(ipAddress);

		if (device == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		if (!updatedDevice.ipDetailsSet()) {
			ErrorMessage errorMessage = new ErrorMessage(400, "Bad Request",
					Arrays.asList(new KeyValuePair("ipAddress", "ipAddress was resolved to " + device.getIpAddress()),
							new KeyValuePair("host", "host was resolved to " + device.getHostName())));

			return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
		}

		deviceService.updateDevice(device);
		return Response.ok(device).build();
	}

	@GET
	@Path("/{ipAddress}/ping")
	@Authorized
	public Response pingDevice(@PathParam("ipAddress") String ipAddress) throws IOException {
		Ping ping = new Ping(ipAddress);

		if (!ping.isValid()) {
			ErrorMessage error = new ErrorMessage(400, "Bad Request",
					Arrays.asList(new KeyValuePair("ipAddress", "ipAddress is invalid")));
			return Response.status(Status.BAD_REQUEST).entity(error).build();
		}
		new SystemProcessExecutor().run(ping);
		return Response.ok(ping.getResponse()).build();
	}
}
