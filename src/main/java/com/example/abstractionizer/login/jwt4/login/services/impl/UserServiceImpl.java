package com.example.abstractionizer.login.jwt4.login.services.impl;

import com.example.abstractionizer.login.jwt4.db.rmdb.mappers.UserMapper;
import com.example.abstractionizer.login.jwt4.login.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean isUserExists(Integer userId, String username) {
        return userMapper.countByIdOrUsername(userId, username) > 0;
    }
}
