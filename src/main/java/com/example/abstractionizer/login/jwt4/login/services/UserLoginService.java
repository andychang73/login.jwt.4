package com.example.abstractionizer.login.jwt4.login.services;

public interface UserLoginService {

     boolean authenticate(String enteredPassword, String password);

     Long countLoginFailure(String key);

     void setUserLoggedIn(String username);

     boolean isUserCurrentlyLoggedIn(String username);
}
