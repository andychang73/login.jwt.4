package com.example.abstractionizer.login.jwt4.login.services;

import com.example.abstractionizer.login.jwt4.db.rmdb.entities.User;

public interface UserRegistrationService {

    void setUserRegisterInfo(String username, User user);

    void setRegisteringUsername(String username);

    boolean isRegisteringNameExists(String username);

    void sendValidationEmail(String to, String uuid);
}
