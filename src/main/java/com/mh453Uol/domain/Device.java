package com.mh453Uol.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.jvnet.hk2.annotations.Optional;

import javax.validation.constraints.NotNull;

@JsonSerialize
public class Device {
	@NotNull(message = "Name must not be empty")
	private String name;
	
	@Optional
	private String ipAddress;
	
	@Optional
	private String hostName;
	
	public Device() {
		
	}
	
	public Device(String name, String ipAddress, String hostName) {
		super();
		this.name = name;
		this.ipAddress = ipAddress;
		this.hostName = hostName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public boolean ipDetailsSet() {
		return ipAddress != null || hostName != null;
	}
}
