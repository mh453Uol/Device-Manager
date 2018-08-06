package com.mh453Uol.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mh453Uol.database.InMemoryDatabase;
import com.mh453Uol.domain.Device;

public class DeviceService {

	public void addDevice(Device device) {
		InMemoryDatabase.devices.put(device.getIpAddress(), device);
	}
	
	public List<Device> getAllDevices() {
		return new ArrayList<Device>(InMemoryDatabase.devices.values());
	}

	public boolean deviceExists(String ipAdress) {
		return InMemoryDatabase.devices.containsKey(ipAdress);
	}

	public Device getDeviceById(String ipAddress) {
		return InMemoryDatabase.devices.get(ipAddress);
	}

	public void updateDevice(Device device) {
		InMemoryDatabase.devices.put(device.getIpAddress(), device);
	}
}
