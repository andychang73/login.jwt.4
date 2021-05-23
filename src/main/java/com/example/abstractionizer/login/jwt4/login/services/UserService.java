package com.example.abstractionizer.login.jwt4.login.services;

import com.example.abstractionizer.login.jwt4.db.rmdb.entities.User;
import com.example.abstractionizer.login.jwt4.models.bo.UpdateInfoBo;
import com.example.abstractionizer.login.jwt4.models.vo.UserInfoVo;

import java.util.Optional;

public interface UserService {

    boolean isUserExists(Integer userId, String username);

    User create(User user);

    Optional<User> getUser(Integer id, String username);

    UserInfoVo getUserInfo(Integer id, String username);

    void freezeAccount(Integer id, boolean status);

    boolean isAccountFrozen(Integer id);

    void updateLastLoginTime(Integer id, UpdateInfoBo bo);

    void updateUserInfo(Integer id, UpdateInfoBo bo);
}
