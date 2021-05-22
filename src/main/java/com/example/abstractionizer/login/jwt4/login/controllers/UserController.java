package com.example.abstractionizer.login.jwt4.login.controllers;

import com.example.abstractionizer.login.jwt4.login.businesses.UserBusiness;
import com.example.abstractionizer.login.jwt4.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.jwt4.models.vo.UserInfoVo;
import com.example.abstractionizer.login.jwt4.responses.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

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
}
