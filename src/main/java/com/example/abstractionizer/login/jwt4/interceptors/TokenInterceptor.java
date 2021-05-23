package com.example.abstractionizer.login.jwt4.interceptors;

import com.example.abstractionizer.login.jwt4.enums.ErrorCode;
import com.example.abstractionizer.login.jwt4.exceptions.CustomException;
import com.example.abstractionizer.login.jwt4.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader("Authorization");
        if(!token.startsWith(JwtUtil.prefix)){
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        return !jwtUtil.isTokenExpired(token.substring(7));
    }
}
