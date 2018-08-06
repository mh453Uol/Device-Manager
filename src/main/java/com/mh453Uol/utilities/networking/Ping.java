package com.mh453Uol.utilities.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

import com.google.common.net.InetAddresses;
import com.mh453Uol.domain.ErrorMessage;
import com.mh453Uol.domain.KeyValuePair;

public class Ping extends Process {
	private String ipAddress;
	private String response;
	private boolean isValid = true;

	public Ping(String ipAddress) {
		this.ipAddress = ipAddress;
		validate();
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	String executeWindowCommand() {
		return "Ping " + ipAddress;
	}

	@Override
	String executeLinuxCommand() {
		throw new UnsupportedOperationException();
	}

	@Override
	String executeMacCommand() {
		throw new UnsupportedOperationException();
	}

	@Override
	String executeSolarisCommand() {
		throw new UnsupportedOperationException();
	}

	@Override
	void processWindowResponse(java.lang.Process output) throws IOException {
		response = "";

		try (BufferedReader standardOutput = new BufferedReader(new InputStreamReader(output.getInputStream()))) {
			String outputLine;

			while ((outputLine = standardOutput.readLine()) != null) {
				response += outputLine + System.lineSeparator();
			}
		}
	}

	@Override
	void processLinuxResponse(java.lang.Process response) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	void processMacResponse(java.lang.Process response) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	void processSolarResponse(java.lang.Process response) throws IOException {
		throw new UnsupportedOperationException();
	}

	public void validate() {
		if (!InetAddresses.isInetAddress(this.ipAddress)) {
			isValid = false;
		}
	}
}
