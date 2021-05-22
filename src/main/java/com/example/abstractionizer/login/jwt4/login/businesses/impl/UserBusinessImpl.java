package com.example.abstractionizer.login.jwt4.login.businesses.impl;

import com.example.abstractionizer.login.jwt4.db.rmdb.entities.User;
import com.example.abstractionizer.login.jwt4.enums.ErrorCode;
import com.example.abstractionizer.login.jwt4.exceptions.CustomException;
import com.example.abstractionizer.login.jwt4.login.businesses.UserBusiness;
import com.example.abstractionizer.login.jwt4.login.services.UserRegistrationService;
import com.example.abstractionizer.login.jwt4.login.services.UserService;
import com.example.abstractionizer.login.jwt4.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.jwt4.models.vo.UserInfoVo;
import com.example.abstractionizer.login.jwt4.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Slf4j
@Service
public class UserBusinessImpl implements UserBusiness {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Override
    public void register(UserRegisterBo bo) {
        if(userService.isUserExists(null, bo.getUsername())){
            throw new CustomException(ErrorCode.USERNAME_EXISTS);
        }
        if(userRegistrationService.isRegisteringNameExists(bo.getUsername())){
            throw new CustomException(ErrorCode.USERNAME_EXISTS);
        }

        User user = new User()
                .setUsername(bo.getUsername())
                .setPassword(MD5Util.md5(bo.getPassword()))
                .setEmail(bo.getEmail())
                .setPhone(bo.getPhone())
                .setStatus(true);

        String uuid = UUID.randomUUID().toString();
        userRegistrationService.setUserRegisterInfo(uuid, user);
        userRegistrationService.setRegisteringUsername(bo.getUsername());
        userRegistrationService.sendValidationEmail(bo.getEmail(), uuid);
    }

    @Override
    public UserInfoVo validate(String uuid) {
        User user = userRegistrationService.getUserRegistrationInfo(uuid).orElseThrow(()-> new CustomException(ErrorCode.ACCOUNT_VALIDATION_EXPIRED));

        userService.create(user);
        userRegistrationService.deleteUserRegistrationInfo(uuid);
        userRegistrationService.deleteRegisteringUsername(user.getUsername());

        return new UserInfoVo()
                .setUserId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setPhone(user.getPhone());
    }
}
