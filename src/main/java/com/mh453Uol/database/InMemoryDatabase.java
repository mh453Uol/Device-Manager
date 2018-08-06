package com.mh453Uol.database;

import java.util.HashMap;
import java.util.Random;

import com.mh453Uol.domain.Device;

public class InMemoryDatabase {
	public static HashMap<String, Device> devices = dummyDevices();

	private static HashMap<String, Device> dummyDevices() {
		HashMap<String, Device> devices = new HashMap<>();
		
		devices.put("100.92.54.176",new Device("mac", "100.92.54.176", "mac book pro"));
		devices.put("4.206.29.32",new Device("t420", "4.206.29.32", "mac kelly"));
		devices.put("57.69.102.32",new Device("secret server", "57.69.102.32", "vm box"));
		
		return devices;
	}
	
	private static String generateRandomIpAddress() {
		//IPV4 address look like <255>.<255>.<255>.<255> i.e 172.31.255.255
		Random randomGenerator = new Random();
		String ip = "";
		
		for(int i=0;i<3;i++) {
			ip += randomGenerator.nextInt(255) + ".";
		}
		ip += randomGenerator.nextInt(255); 
		
		return ip;
	}
	
	
}
