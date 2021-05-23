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
        redisUtil.set(key, count, 5L, TimeUnit.MINUTES);
        return count;
    }

    @Override
    public void setUserLoggedIn(String username) {
        redisUtil.set(RedisConstant.getUserLoggedIn(username), username, JwtUtil.validity, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isUserCurrentlyLoggedIn(String username) {
        return redisUtil.isKeyExists(RedisConstant.getUserLoggedIn(username));
    }

}
