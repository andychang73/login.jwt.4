package com.example.abstractionizer.login.jwt4.login.services.impl;

import com.example.abstractionizer.login.jwt4.db.rmdb.entities.User;
import com.example.abstractionizer.login.jwt4.db.rmdb.mappers.UserMapper;
import com.example.abstractionizer.login.jwt4.enums.ErrorCode;
import com.example.abstractionizer.login.jwt4.exceptions.CustomException;
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

    @Override
    public User create(User user) {
        if(userMapper.insert(user) != 1){
            throw new CustomException(ErrorCode.DATA_INSERT_FAILED);
        }
        return user;
    }
}
