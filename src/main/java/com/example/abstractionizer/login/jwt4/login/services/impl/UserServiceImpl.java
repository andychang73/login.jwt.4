package com.example.abstractionizer.login.jwt4.login.services.impl;

import com.example.abstractionizer.login.jwt4.db.rmdb.entities.User;
import com.example.abstractionizer.login.jwt4.db.rmdb.mappers.UserMapper;
import com.example.abstractionizer.login.jwt4.enums.ErrorCode;
import com.example.abstractionizer.login.jwt4.exceptions.CustomException;
import com.example.abstractionizer.login.jwt4.login.services.UserService;
import com.example.abstractionizer.login.jwt4.models.bo.UpdateInfoBo;
import com.example.abstractionizer.login.jwt4.models.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

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

    @Override
    public Optional<User> getUser(Integer id, String username) {
        return Optional.ofNullable(userMapper.selectByIdOrUsername(id, username));
    }

    @Override
    public UserInfoVo getUserInfo(Integer id, String username) {
        return userMapper.getByIdOrUsername(id, username);
    }

    @Override
    public void freezeAccount(Integer id, boolean status) {
        if(userMapper.updateStatus(id, status) != 1){
            throw new CustomException(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public boolean isAccountFrozen(Integer id) {
        return userMapper.getStatus(id);
    }

    @Override
    public void updateLastLoginTime(Integer id, UpdateInfoBo bo) {
        if(userMapper.updateUserInfo(id, bo) != 1){
            throw new CustomException(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void updateUserInfo(Integer id, UpdateInfoBo bo) {
        if(userMapper.updateUserInfo(id, bo) != 1){
            throw new CustomException(ErrorCode.DATA_UPDATE_FAILED);
        }
    }
}
