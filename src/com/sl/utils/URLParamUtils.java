package com.sl.utils;

public class URLParamUtils {
	public static String getProperty(String param, String property) {
		String[] arr = param.split("&");
		for (String p : arr) {
			String tmp[] = p.split("=");
			if (tmp[0].equalsIgnoreCase(property)) {
				return tmp[1];
			}
		}
		return null;
	}

}
