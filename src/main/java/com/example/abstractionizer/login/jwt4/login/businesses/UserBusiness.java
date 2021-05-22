package com.example.abstractionizer.login.jwt4.login.businesses;

import com.example.abstractionizer.login.jwt4.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.jwt4.models.vo.UserInfoVo;

public interface UserBusiness {

    void register(UserRegisterBo bo);

    UserInfoVo validate(String uuid);
}
