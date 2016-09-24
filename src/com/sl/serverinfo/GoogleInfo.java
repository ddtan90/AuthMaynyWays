package com.sl.serverinfo;

public class GoogleInfo extends ServerInfo{
	
	public GoogleInfo() {
		setAppId("713078684886-fsbv4kbc71hj98lktc6ieq8689ool64n.apps.googleusercontent.com");
		setSecretKey("pcpo7r-Aj5SjAJltMbeMhOub");
		setRedirectUrl("http://localhost:8080/GoogleAuth/googlecallback");
		setTokenUrl("https://accounts.google.com/o/oauth2/token");
		setInfoUrl("https://www.googleapis.com/oauth2/v1/userinfo?access_token=");
	}

}
