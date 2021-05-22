package com.example.abstractionizer.login.jwt4.constants;

public class RedisConstant {

    public static final String USER_REGISTRATION = "LOGIN:USER:REGISTRATION:%s";
    public static final String REGISTERING_USER_NAME = "LOGIN:REGISTERING:USER:%s";

    public static String getUserRegistration(String username){
        return String.format(USER_REGISTRATION, username);
    }

    public static String getRegisteringUserName(String username){
        return String.format(REGISTERING_USER_NAME, username);
    }
}