package com.example.abstractionizer.login.jwt4.login.services;

public interface UserService {

    boolean isUserExists(Integer userId, String username);
}
