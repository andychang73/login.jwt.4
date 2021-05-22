package com.example.abstractionizer.login.jwt4.login.services;

import com.example.abstractionizer.login.jwt4.db.rmdb.entities.User;

public interface UserService {

    boolean isUserExists(Integer userId, String username);

    User create(User user);
}
