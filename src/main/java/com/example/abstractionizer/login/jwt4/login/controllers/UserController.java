package com.example.abstractionizer.login.jwt4.login.controllers;

import com.example.abstractionizer.login.jwt4.login.businesses.UserBusiness;
import com.example.abstractionizer.login.jwt4.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.jwt4.responses.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
