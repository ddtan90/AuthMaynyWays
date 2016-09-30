package com.sl;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginLDAP")
public class ActiveDirectoryLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String LDAP_SERVER = "192.168.4.72";
	private static final String LDAP_SERVER_PORT = "389";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException {
		String msg = "Please input Username and password to log in.";
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("/index.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		System.out.println("Credentials: " + username + " - " + password);

		if (authen(username, password)) {
			UserPojo user = new UserPojo();
			user.setId(username);
			user.setEmail(username);
			user.setName(username);

			req.setAttribute("auth", user);
			req.getRequestDispatcher("/info.jsp").forward(req, res);

		} else {
			String msg = "Username or Password you input is not correct. Please try again.";
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("/index.jsp").forward(req, res);
		}
	}

	private boolean authen(String username, String password) {
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + LDAP_SERVER + ":" + LDAP_SERVER_PORT);

		// To get rid of the PartialResultException when using Active Directory
		env.put(Context.REFERRAL, "follow");

		// Needed for the Bind (User Authorized to Query the LDAP server)
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			new InitialDirContext(env);
			return true;
		} catch (NamingException e) {
			e.printStackTrace();
			return false;
		}
	}

}
