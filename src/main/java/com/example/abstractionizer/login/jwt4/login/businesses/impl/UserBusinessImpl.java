package com.example.abstractionizer.login.jwt4.login.businesses.impl;

import com.example.abstractionizer.login.jwt4.constants.RedisConstant;
import com.example.abstractionizer.login.jwt4.db.rmdb.entities.User;
import com.example.abstractionizer.login.jwt4.enums.ErrorCode;
import com.example.abstractionizer.login.jwt4.exceptions.CustomException;
import com.example.abstractionizer.login.jwt4.login.businesses.UserBusiness;
import com.example.abstractionizer.login.jwt4.login.services.UserLoginService;
import com.example.abstractionizer.login.jwt4.login.services.UserRegistrationService;
import com.example.abstractionizer.login.jwt4.login.services.UserService;
import com.example.abstractionizer.login.jwt4.models.bo.ChangePasswordBo;
import com.example.abstractionizer.login.jwt4.models.bo.UpdateInfoBo;
import com.example.abstractionizer.login.jwt4.models.bo.UserLoginBo;
import com.example.abstractionizer.login.jwt4.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.jwt4.models.vo.UserInfoVo;
import com.example.abstractionizer.login.jwt4.models.vo.UserLoginVo;
import com.example.abstractionizer.login.jwt4.utils.JwtUtil;
import com.example.abstractionizer.login.jwt4.utils.MD5Util;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class UserBusinessImpl implements UserBusiness {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private JwtUtil jwtUtil;

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

    @SneakyThrows
    @Override
    public UserLoginVo login(UserLoginBo bo) {
        User user = userService.getUser(null, bo.getUsername()).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(!userService.isAccountFrozen(user.getId())){
            throw new CustomException(ErrorCode.ACCOUNT_FROZEN);
        }
        if(userLoginService.isUserCurrentlyLoggedIn(user.getUsername())){
            throw new CustomException(ErrorCode.USER_LOGGED_IN);
        }

        if(!userLoginService.authenticate(MD5Util.md5(bo.getPassword()), user.getPassword())){
            if(userLoginService.countLoginFailure(RedisConstant.getLoginFailureCount(bo.getUsername())) >= 3){
                userService.freezeAccount(user.getId(), false);
                throw new CustomException(ErrorCode.ACCOUNT_FROZEN);
            }
        }

        UserInfoVo userInfovo = new UserInfoVo()
                .setUserId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setPhone(user.getPhone());

        final String token = jwtUtil.generateToken(user.getUsername(), userInfovo);
        userService.updateLastLoginTime(user.getId(), new UpdateInfoBo().setLastLoginTime(new Date()));
        userLoginService.setUserLoggedIn(user.getUsername());

        return new UserLoginVo().setToken(token);
    }

    @Override
    public UserInfoVo updateInfo(Integer userId, UpdateInfoBo bo) {
        if(!userService.isUserExists(userId, null)){
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        userService.updateUserInfo(userId, bo);
        return userService.getUserInfo(userId, bo.getUsername());
    }

    @Override
    public void changePassword(ChangePasswordBo bo) {

    }
}
