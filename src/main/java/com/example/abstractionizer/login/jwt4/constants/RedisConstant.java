package com.example.abstractionizer.login.jwt4.constants;

public class RedisConstant {

    public static final String USER_REGISTRATION = "LOGIN:USER:REGISTRATION:%s";
    public static final String REGISTERING_USER_NAME = "LOGIN:REGISTERING:USER:%s";
    public static final String LOGIN_FAILURE_COUNT = "LOGIN:FAILURE:COUNT:%s";
    public static final String USER_LOGGED_IN = "LOGIN:USER:LOGGED_IN:%s";
    public static final String USER_LOG_OUT = "LOGIN:USER:LOGOUT:%s";

    public static String getUserRegistration(String username){
        return String.format(USER_REGISTRATION, username);
    }

    public static String getRegisteringUserName(String username){
        return String.format(REGISTERING_USER_NAME, username);
    }

    public static String getLoginFailureCount(String username){
        return String.format(LOGIN_FAILURE_COUNT, username);
    }

    public static String getUserLoggedIn(Integer userId){
        return String.format(USER_LOGGED_IN, userId);
    }

    public static String getUserLogOut(String token){
        return String.format(USER_LOG_OUT, token);
    }
}
