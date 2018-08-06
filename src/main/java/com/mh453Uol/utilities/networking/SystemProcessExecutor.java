package com.mh453Uol.utilities.networking;

import java.io.IOException;
import com.mh453Uol.utilities.system.OperatingSystem;

public class SystemProcessExecutor {

	public void run(Process process) throws IOException {

		java.lang.Process output = null;

		switch (OperatingSystem.getOS()) {
		case WINDOWS:
			output = Runtime.getRuntime().exec(process.executeWindowCommand());
			process.processWindowResponse(output);
			break;
		case MAC:
			output = Runtime.getRuntime().exec(process.executeMacCommand());
			process.processMacResponse(output);
			break;
		case LINUX:
			output = Runtime.getRuntime().exec(process.executeLinuxCommand());
			process.processLinuxResponse(output);
			break;
		case SOLARIS:
			output = Runtime.getRuntime().exec(process.executeSolarisCommand());
			process.processSolarResponse(output);
			break;
		default:
			break;
		}
	}
}
