package com.sl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sl.serverinfo.FacebookInfo;

@WebServlet("/facebookcallback")
public class FacebookCallback extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static String className = "com.sl.Oauth2callback";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String code = request.getParameter("code");

			FacebookInfo info = new FacebookInfo();
			URL url = new URL("https://graph.facebook.com/oauth/access_token?client_id=" + info.getAppId() + "&redirect_uri="
					+ info.getRedirectUrl() + "&client_secret=" + info.getSecretKey() + "&code=" + code);

			String line1 = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader((InputStream) url.getContent()));
			String line;
			while ((line = reader.readLine()) != null) {
				line1 = line1 + line;
			}
			String[] arr = line1.split("&");
			String token = "";
			for (String p : arr) {
				String tmp[] = p.split("=");
				if (tmp[0].equals("access_token")) {
					token = tmp[1];
					break;
				}
			}
			url = new URL("https://graph.facebook.com/me?access_token=" + token);
			line1 = "";
			reader = new BufferedReader(new InputStreamReader((InputStream) url.getContent()));
			while ((line = reader.readLine()) != null) {
				line1 = line1 + line;
			}
			UserPojo data = (UserPojo) new Gson().fromJson(line1, UserPojo.class);
			// writer.close();
			reader.close();
			request.setAttribute("auth", data);
			request.getRequestDispatcher("/info.jsp").forward(request, response);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
