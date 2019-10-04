package com.mephistosoftware.rester.security;

public class SecurityConstants {
	
    public static final String SECRET = "asdkf;alkj3poiupaijsw;lfkdja;wijepaiojws;ldfja;sliefpaoij;flak";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "JWT ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/register";
    public static final String APP_LOGIN = "/app-login";
    public static final String WEBSITE_REGISTER = "/website-login";
    public static final String TOKEN = "token";
    public static final Integer UNASSIGNED = 0;
    public static final Integer TEACHER = 1;
    public static final Integer SCHOOL = 2;
    public static final Integer ADMIN = 999;

}
