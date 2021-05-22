package com.example.abstractionizer.login.jwt4.login.services;

import com.example.abstractionizer.login.jwt4.db.rmdb.entities.User;

import java.util.Optional;

public interface UserRegistrationService {

    void setUserRegisterInfo(String uuid, User user);

    Optional<User> getUserRegistrationInfo(String username);

    void deleteUserRegistrationInfo(String uuid);

    void setRegisteringUsername(String username);

    void deleteRegisteringUsername(String username);

    boolean isRegisteringNameExists(String username);

    void sendValidationEmail(String to, String uuid);


}
