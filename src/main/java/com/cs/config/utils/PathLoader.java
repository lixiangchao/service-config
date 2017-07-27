package com.cs.config.utils;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

public class PathLoader {
	private static VersionConfig[] versionConfigs;
	private static String defaultPath = "E:/config/";
	static {
		try {
			InputStream pathIni = PathLoader.class.getResourceAsStream("/PathMapper.json");
			ObjectMapper objectMapper = new ObjectMapper();
			versionConfigs = objectMapper.readValue(pathIni, VersionConfig[].class);
		} catch (IOException e) {
		}
	}

	public static String getPath(String proVersion, String filePath) {
		String path = null;
		for (VersionConfig versionConfig : versionConfigs) {
			if (versionConfig.version.equals(proVersion)) {
				path = versionConfig.path;
			}
		}
		if (path == null) {
			path = defaultPath + proVersion + "/" + filePath;
		} else {
			path = path + "/" + filePath;
		}
		System.out.println(path);
		return path;
	}

	public static void main(String args[]) {
		System.out.println("/");
	}
}
