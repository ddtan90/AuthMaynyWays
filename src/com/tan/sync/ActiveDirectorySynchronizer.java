package com.tan.sync;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class ActiveDirectorySynchronizer {

	private static final String LDAP_SERVER = "192.168.4.72";
	private static final String LDAP_SERVER_PORT = "389";

	private static final String USERNAME = "TAN\\Administrator";
	private static final String PASSWORD = "12345678x@X";

	public static String usersContainer = "OU=tenant01,OU=Domain Controllers,DC=tan,DC=local";// cn=XX,ou=XX,ou=Groups,dc=XX,

	static {
		Timer t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				InitialDirContext context = authen(USERNAME, PASSWORD);
				List<String> activeDirectoryUsers;
				try {
					activeDirectoryUsers = getUsersFromActiveDirectory(context);

					List<String> brekekeUsers = getBrekekeUsers(0);

					for (String u : brekekeUsers) {
						if (activeDirectoryUsers.contains(u)) {
							System.out.println("User " + u + " is still available.");
							activeDirectoryUsers.remove(u);
						} else {
							System.out.println("User " + u + " is NOT avilable on Active Directory. Change uer status to INVALID");
						}
					}

					for (String u : activeDirectoryUsers) {
						System.out.println("User " + u + " is new for Brekeke. Added.");
					}

				} catch (NamingException e) {
					e.printStackTrace();
				}

			}
		}, 0, 1000 * 60 * 60 * 24); // run once a day

	}

	public static InitialDirContext authen(String username, String password) {
		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + LDAP_SERVER + ":" + LDAP_SERVER_PORT);

		env.put(Context.REFERRAL, "follow");

		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			InitialDirContext context = new InitialDirContext(env);
			System.out.println("Log in successfully as user " + username);

			return context;
		} catch (NamingException e) {
			System.out.println("Log in failed.");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method is mockup that get list of all user in the tenant
	 * 
	 * @param tenentId
	 *            the TenantId
	 * @return list of userername
	 */
	public static List<String> getBrekekeUsers(int tenentId) {
		List<String> retval = new ArrayList<String>();

		retval.add("tandao");
		retval.add("Administrator");
		retval.add("Guest");
		retval.add("it01");
		retval.add("test");
		retval.add("wrong");

		return retval;
	}

	/**
	 * Get the list of all user from Active directory server
	 * 
	 * @param context
	 * @return
	 * @throws NamingException
	 */
	public static List<String> getUsersFromActiveDirectory(InitialDirContext context) throws NamingException {
		List<String> retval = new ArrayList<String>();

		SearchControls ctls = new SearchControls();
		String[] attrIDs = { "distinguishedName", "cn", "name", "uid", "sn", "givenname", "memberOf", "samaccountname", "userPrincipalName" };

		ctls.setReturningAttributes(attrIDs);
		ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		NamingEnumeration answer = context.search(usersContainer, "(objectClass=user)", ctls);

		try {
			System.out.println("Getting userlist from Active directory server. It'll take a while...");
			while (answer.hasMoreElements()) {
				SearchResult rslt = (SearchResult) answer.next();
				Attributes attrs = rslt.getAttributes();

				Attribute samaccountname = attrs.get("samaccountname");
				String username = (String) samaccountname.get();

				// Mayby we need to check the domain or group more

				retval.add(username);
			}
			context.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retval;

	}

}
