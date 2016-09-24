package com.sl;

import com.google.gson.Gson;
import com.sl.serverinfo.GoogleInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/googlecallback")
public class GoogleCallback extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static String className = "com.sl.Oauth2callback";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			GoogleInfo info = new GoogleInfo();
			String code = request.getParameter("code");
			String urlParameters = "code=" + code + "&client_id=" + info.getAppId() + "&client_secret=" + info.getSecretKey()
					+ "&redirect_uri=" + info.getRedirectUrl() + "&grant_type=authorization_code";
			URL url = new URL("https://accounts.google.com/o/oauth2/token");
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
			String s = GsonUtility.getJsonElementString("access_token", line1);

			url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + s);
			conn = url.openConnection();
			line1 = "";
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				line1 = line1 + line;
			}
			UserPojo data = (UserPojo) new Gson().fromJson(line1, UserPojo.class);
			writer.close();
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
