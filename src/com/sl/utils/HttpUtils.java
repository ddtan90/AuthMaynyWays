package com.sl.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {

	public static String get(URL url) throws IOException {
		String line1 = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader((InputStream) url.getContent()));
		String line;
		while ((line = reader.readLine()) != null) {
			line1 = line1 + line;
		}
		return line1;
	}

	public static String post(URL url, String urlParameters) throws IOException {
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write(urlParameters);
		writer.flush();
		String line1 = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			line1 = line1 + line;
		}
		return line1;
	}
}
