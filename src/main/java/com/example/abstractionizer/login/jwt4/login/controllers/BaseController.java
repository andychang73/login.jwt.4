package com.example.abstractionizer.login.jwt4.login.controllers;


import com.example.abstractionizer.login.jwt4.models.vo.UserInfoVo;
import com.example.abstractionizer.login.jwt4.utils.JwtUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    private JwtUtil jwtUtil;

    @SneakyThrows
    protected UserInfoVo getUserInfo(String token){
        return jwtUtil.getUserInfoFromToken(token.substring(7));
    }
}
