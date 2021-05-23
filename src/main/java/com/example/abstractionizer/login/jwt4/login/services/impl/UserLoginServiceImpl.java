package com.example.abstractionizer.login.jwt4.login.services.impl;

import com.example.abstractionizer.login.jwt4.constants.RedisConstant;
import com.example.abstractionizer.login.jwt4.login.services.UserLoginService;
import com.example.abstractionizer.login.jwt4.utils.JwtUtil;
import com.example.abstractionizer.login.jwt4.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {

    private final Long loginFailureDuration = 5l;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean authenticate(String enteredPassword, String password) {
        return Objects.equals(enteredPassword, password);
    }

    @Override
    public Long countLoginFailure(String key) {
        Long count = 1L;
        if(redisUtil.isKeyExists(key)){
            return redisUtil.increment(key, count);
        }
        redisUtil.set(key, count, loginFailureDuration, TimeUnit.MINUTES);
        return count;
    }

    @Override
    public void setUserLoggedIn(Integer userId) {
        redisUtil.set(RedisConstant.getUserLoggedIn(userId), userId, JwtUtil.validity, TimeUnit.MILLISECONDS);
    }

    @Override
    public void deleteLoggedInUser(Integer userId) {
        redisUtil.deleteKey(RedisConstant.getUserLoggedIn(userId));
    }

    @Override
    public boolean isUserCurrentlyLoggedIn(Integer userId) {
        return redisUtil.isKeyExists(RedisConstant.getUserLoggedIn(userId));
    }

    @Override
    public void logOut(String token, Long duration) {
        redisUtil.set(RedisConstant.getUserLogOut(token), token, duration, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isUserLoggedOut(String token) {
        return redisUtil.isKeyExists(RedisConstant.getUserLogOut(token));
    }

}
