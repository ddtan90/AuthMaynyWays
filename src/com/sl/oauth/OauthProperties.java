package com.sl.oauth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OauthProperties {

	static private Properties properties;
	static {
		properties = new Properties();
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("gg.properties");
		try {
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String prop, String defaultVal) {
		return properties.getProperty(prop, defaultVal);
	}

	public static String getProperty(String prop) {
		return properties.getProperty(prop);
	}

}
