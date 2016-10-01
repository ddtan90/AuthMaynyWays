package com.sl.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {

	// this method is only used for testing.DO not use it on product
	public static String getProperty(String json, String property) {
		JsonElement e = new JsonParser().parse(json);
		String[] arr = property.split("\\.");
		for (String p : arr) {
			if (e != null && !e.isJsonNull()) {
				e = ((JsonObject) e).get(p);
			} else {
				return null;
			}
		}
		return e.getAsString();
	}

}
