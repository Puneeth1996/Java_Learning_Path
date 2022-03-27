package com.developerblog.app.ws.security;

import com.developerblog.app.ws.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    // commenting this below line as we are defining the tokenSecret in the application.properties and
    // reading it from the AppProperties.java file by creating new bean to read all the properties aswell
    // public static final String TOKEN_SECRET = "jf9i4jgu83nfl0jfu57ejf7";
    
	
	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
		return appProperties.getTokenSecret();
    }
}

