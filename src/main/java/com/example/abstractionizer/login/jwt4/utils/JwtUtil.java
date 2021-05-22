package com.example.abstractionizer.login.jwt4.utils;

import com.example.abstractionizer.login.jwt4.models.vo.UserInfoVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Autowired
    private ObjectMapper objectMapper;

    public static final Long validity = 1000 * 60 * 60 * 24L;
    public static final String prefix = "Bearer ";
    private final String secretKey = "fjkeidjfqpicjqkfidopvnqhfiodqkmvfljqnfiuhdquoifhdiqu";


    public boolean isTokenValid(String token, String username){
        return getClaimFromToken(token, Claims::getSubject).equals(username) && !isTokenExpired(token);
    }

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token){
        return getClaimFromToken(token, Claims::getExpiration).after(new Date());
    }

    public Date getExpirationFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public UserInfoVo getUserInfoFromToken(String token) throws JsonProcessingException {
        return decode(getAllFromToken(token).get("UserInfo", String.class));
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> resolver){
        return resolver.apply(getAllFromToken(token));
    }

    private Claims getAllFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String generateToken(String username, UserInfoVo userInfoVo) throws JsonProcessingException {
        return Jwts.builder()
                .setSubject(username)
                .claim("UserInfo", encode(userInfoVo))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private String encode(UserInfoVo u) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(u);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(bytes);
    }

    private UserInfoVo decode(String s) throws JsonProcessingException {
        byte[] bytes = Base64.getDecoder().decode(s);
        String json = new String(bytes, StandardCharsets.UTF_8);
        return objectMapper.readValue(json, UserInfoVo.class);
    }
}
