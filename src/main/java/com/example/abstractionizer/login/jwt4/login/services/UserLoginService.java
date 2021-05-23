package com.example.abstractionizer.login.jwt4.login.services;

public interface UserLoginService {

     boolean authenticate(String enteredPassword, String password);

     Long countLoginFailure(String key);

     void setUserLoggedIn(Integer userId);

     void deleteLoggedInUser(Integer userId);

     boolean isUserCurrentlyLoggedIn(Integer userId);

     void logOut(String token, Long duration);

     boolean isUserLoggedOut(String token);
}
