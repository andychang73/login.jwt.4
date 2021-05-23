package com.example.abstractionizer.login.jwt4.interceptors;

import com.example.abstractionizer.login.jwt4.enums.ErrorCode;
import com.example.abstractionizer.login.jwt4.exceptions.CustomException;
import com.example.abstractionizer.login.jwt4.login.services.UserLoginService;
import com.example.abstractionizer.login.jwt4.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserLoginService userLoginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader("Authorization");
        if(!token.startsWith(JwtUtil.prefix)){
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        if(userLoginService.isUserLoggedOut(token.substring(7))){
            throw new CustomException(ErrorCode.USER_LOGGED_OUT);
        }
        return !jwtUtil.isTokenExpired(token.substring(7));
    }
}
