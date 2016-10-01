package com.sl.oauth;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sl.utils.HttpUtils;
import com.sl.utils.JsonUtils;
import com.sl.utils.URLParamUtils;

@WebServlet("/oauthcallback")
public class OAuthenCallback extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String code = request.getParameter(OauthProperties.getProperty("oauthen.callbackCodeParam"));
			String token = getToken(request, response, code);
			UserPojo data = getUserInfo(request, response, token);

			request.setAttribute("auth", data);
			request.getRequestDispatcher("/info.jsp").forward(request, response);
		} catch (Exception e) {
			directToError(request, response, e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	private String getToken(HttpServletRequest request, HttpServletResponse response, String code) throws Exception {
		String urlStr = OauthProperties.getProperty("oauthen.token.URL").replace("{{code}}", code);

		URL tokenUrl = new URL(urlStr);
		String tokenMethod = OauthProperties.getProperty("oauthen.token.method");
		String tokenReturn = "";
		if ("GET".equalsIgnoreCase(tokenMethod)) {
			tokenReturn = HttpUtils.get(tokenUrl);
		} else if ("POST".equalsIgnoreCase(tokenMethod)) {
			String tokenParam = OauthProperties.getProperty("oauthen.token.param").replace("{{code}}", code);
			tokenReturn = HttpUtils.post(tokenUrl, tokenParam);
		} else {
			throw new Exception("Get token: Method [" + tokenMethod + "] is not supported");
		}

		String token = null;
		if (tokenReturn != null) {
			String tokenReturnType = OauthProperties.getProperty("oauthen.token.return.type");
			String tokenPName = OauthProperties.getProperty("oauthen.token.return.property.token");
			if ("URL".equalsIgnoreCase(tokenReturnType)) {
				token = URLParamUtils.getProperty(tokenReturn, tokenPName);
			} else if ("JSON".equalsIgnoreCase(tokenReturnType)) {
				token = JsonUtils.getProperty(tokenReturn, tokenPName);
			} else {
				throw new Exception("Parse token: Method [" + tokenReturnType + "] is not supported");
			}
		}

		return token;
	}

	private UserPojo getUserInfo(HttpServletRequest request, HttpServletResponse response, String token) throws Exception {
		String urlStr = OauthProperties.getProperty("oauthen.resource.URL").replace("{{token}}", token);

		URL resourceUrl = new URL(urlStr);
		String resourceMethod = OauthProperties.getProperty("oauthen.resource.method");
		String resourceReturn = "";
		if ("GET".equalsIgnoreCase(resourceMethod)) {
			resourceReturn = HttpUtils.get(resourceUrl);
		} else if ("POST".equalsIgnoreCase(resourceMethod)) {
			String resourceParam = OauthProperties.getProperty("oauthen.resource.param").replace("{{token}}", token);
			resourceReturn = HttpUtils.post(resourceUrl, resourceParam);
		} else {
			throw new Exception("Get resource: Method [" + resourceMethod + "] is not supported");
		}

		UserPojo user = new UserPojo();
		if (resourceReturn != null) {
			String resourceReturnType = OauthProperties.getProperty("oauthen.resource.return.type");
			String idPName = OauthProperties.getProperty("oauthen.resource.return.property.userId");
			String userNamePName = OauthProperties.getProperty("oauthen.resource.return.property.userName");
			if ("URL".equalsIgnoreCase(resourceReturnType)) {
				user.setId(URLParamUtils.getProperty(resourceReturn, idPName));
				user.setName(URLParamUtils.getProperty(resourceReturn, userNamePName));
			} else if ("JSON".equalsIgnoreCase(resourceReturnType)) {
				user.setId(JsonUtils.getProperty(resourceReturn, idPName));
				user.setName(JsonUtils.getProperty(resourceReturn, userNamePName));
			} else {
				throw new Exception("Parse resource: Method [" + resourceReturnType + "] is not supported");
			}
		}
		return user;
	}

	private void directToError(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
		request.setAttribute("errMsg", msg);
		request.getRequestDispatcher("/error.jsp").forward(request, response);
		return;
	}

}
