package com.example.abstractionizer.login.jwt4.login.services.impl;

import com.example.abstractionizer.login.jwt4.db.rmdb.entities.User;
import com.example.abstractionizer.login.jwt4.login.services.UserRegistrationService;
import com.example.abstractionizer.login.jwt4.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

import static com.example.abstractionizer.login.jwt4.constants.RedisConstant.getRegisteringUserName;
import static com.example.abstractionizer.login.jwt4.constants.RedisConstant.getUserRegistration;

@Slf4j
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void setUserRegisterInfo(String username, User user) {
        redisUtil.set(getUserRegistration(username), user, 5L, TimeUnit.MINUTES);
    }

    @Override
    public void setRegisteringUsername(String username) {
        redisUtil.set(getRegisteringUserName(username), username, 5L, TimeUnit.MINUTES);
    }

    @Override
    public boolean isRegisteringNameExists(String username) {
        return redisUtil.isKeyExists(getRegisteringUserName(username));
    }

    @Override
    public void sendValidationEmail(String to, String uuid) {
        javaMailSender.send(getMessage(to, uuid));
    }

    private SimpleMailMessage getMessage(String to,String uuid){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@abstractionizer.com");
        message.setTo(to);
        message.setSubject("Account Validation");
        message.setText(getValidationUrl(uuid));
        return message;
    }

    private String getValidationUrl(String uuid){
        return "http://127.0.0.1:8080/api/user?token=" + uuid;
    }
}
