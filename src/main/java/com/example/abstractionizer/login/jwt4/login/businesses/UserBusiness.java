package com.example.abstractionizer.login.jwt4.login.businesses;

import com.example.abstractionizer.login.jwt4.models.bo.ChangePasswordBo;
import com.example.abstractionizer.login.jwt4.models.bo.UpdateInfoBo;
import com.example.abstractionizer.login.jwt4.models.bo.UserLoginBo;
import com.example.abstractionizer.login.jwt4.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.jwt4.models.vo.UserInfoVo;
import com.example.abstractionizer.login.jwt4.models.vo.UserLoginVo;

public interface UserBusiness {

    void register(UserRegisterBo bo);

    UserInfoVo validate(String uuid);

    UserLoginVo login(UserLoginBo bo);

    UserInfoVo updateInfo(Integer userId, UpdateInfoBo bo);

    void changePassword(ChangePasswordBo bo);
}
