package com.mh453Uol.utilities.system;

public class OperatingSystem {
	public static enum OS {
		WINDOWS, LINUX, MAC, SOLARIS
	};

	public static OS getOS() {
		String operatingSystem = System.getProperty("os.name").toLowerCase();
		
		if (operatingSystem.contains("win")) {
			return OS.WINDOWS;
		} else if (operatingSystem.contains("nix") || operatingSystem.contains("nux")
				|| operatingSystem.contains("aix")) {
			return OS.LINUX;
		} else if (operatingSystem.contains("mac")) {
			return OS.MAC;
		} else if (operatingSystem.contains("sunos")) {
			return OS.SOLARIS;
		}
        throw new UnsupportedOperationException("Unsupported operating system");
	}
}
