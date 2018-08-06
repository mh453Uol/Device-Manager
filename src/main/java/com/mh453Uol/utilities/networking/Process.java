package com.mh453Uol.utilities.networking;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class Process {
	abstract String executeWindowCommand();
	abstract String executeLinuxCommand();
	abstract String executeMacCommand();
	abstract String executeSolarisCommand();
	
	abstract void processWindowResponse(java.lang.Process process) throws IOException;
	abstract void processLinuxResponse(java.lang.Process process) throws IOException;
	abstract void processMacResponse(java.lang.Process process) throws IOException;
	abstract void processSolarResponse(java.lang.Process process) throws IOException;
}
