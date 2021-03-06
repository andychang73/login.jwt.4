package com.example.abstractionizer.login.jwt4.login.controllers;

import com.example.abstractionizer.login.jwt4.login.businesses.UserBusiness;
import com.example.abstractionizer.login.jwt4.models.bo.ChangePasswordBo;
import com.example.abstractionizer.login.jwt4.models.bo.UpdateInfoBo;
import com.example.abstractionizer.login.jwt4.models.bo.UserLoginBo;
import com.example.abstractionizer.login.jwt4.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.jwt4.models.vo.UserInfoVo;
import com.example.abstractionizer.login.jwt4.models.vo.UserLoginVo;
import com.example.abstractionizer.login.jwt4.responses.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{

    @Autowired
    private UserBusiness userBusiness;

    @PostMapping
    public SuccessResponse register(@RequestBody @Valid UserRegisterBo bo){
        userBusiness.register(bo);
        return new SuccessResponse();
    }

    @GetMapping
    public SuccessResponse<UserInfoVo> validate(@RequestParam("token") String uuid){
        return new SuccessResponse<>(userBusiness.validate(uuid));
    }

    @PostMapping("/login")
    public SuccessResponse<UserLoginVo> login(@RequestBody @Valid UserLoginBo bo){
        return new SuccessResponse<>(userBusiness.login(bo));
    }

    @PutMapping("/update")
    public SuccessResponse<UserInfoVo> update(@RequestHeader("Authorization") String token,
                                              @RequestBody @Valid UpdateInfoBo bo){
        return new SuccessResponse<>(userBusiness.updateInfo(this.getUserInfo(token).getUserId(), bo));
    }

    @PutMapping("/changePassword")
    public SuccessResponse changePassword(@RequestHeader("Authorization") String token,
                                          @RequestBody @Valid ChangePasswordBo bo){
        userBusiness.changePassword(this.getUserInfo(token).getUserId(),bo);
        return new SuccessResponse();
    }

    @GetMapping("/logOut")
    public SuccessResponse logOut(@RequestHeader("Authorization") String token){
        userBusiness.logOut(this.getUserInfo(token).getUserId(), token.substring(7));
        return new SuccessResponse();
    }
}
