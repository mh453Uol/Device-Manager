package com.mh453Uol.services;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpService {
	public static InetAddress getIpAddress() {
		InetAddress ipAddress = null;
		
		try {
			ipAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
		return ipAddress;
	}
}
