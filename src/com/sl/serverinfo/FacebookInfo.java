package com.sl.serverinfo;

public class FacebookInfo extends ServerInfo{
	
	public FacebookInfo() {
		setAppId("1745111929082181");
		setSecretKey("bbaae4aca80cd18c7a34952c917e340a");
		setRedirectUrl("http://localhost:8080/GoogleAuth/facebookcallback");
		
		
		setTokenUrl("https://accounts.google.com/o/oauth2/token");
		setInfoUrl("https://www.googleapis.com/oauth2/v1/userinfo?access_token=");
	}

}
