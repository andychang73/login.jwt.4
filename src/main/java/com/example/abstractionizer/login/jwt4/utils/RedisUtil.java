package com.example.abstractionizer.login.jwt4.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public boolean set(@NonNull final String key, @NonNull final Object obj, @NonNull final Long duration, @NonNull final TimeUnit timeUnit){
        try{
            this.redisTemplate.boundValueOps(key).set(obj, duration, timeUnit);
            return true;
        }catch(Exception var1){
            try{
                log.error("Write obj failed, key: {} obj: {}", this.objectMapper.writeValueAsBytes(obj), var1);
            }catch(JsonProcessingException var2){
                log.error("Write obj to json error:", var2);
            }
        }
        return false;
    }

    public <T> T get(@NonNull final String key, @NonNull final Class<T> cls){
        Object obj;
        try{
            obj = this.redisTemplate.boundValueOps(key).get();
        }catch(Exception var3){
            log.error("Get obj failed, key : {}", key);
            return null;
        }
        return cls.cast(obj);
    }

    public boolean isKeyExists(@NonNull final String key){
        if(key.isEmpty()){
            return false;
        }else{
            return !Objects.isNull(this.get(key, Object.class));
        }
    }

    public Long increment(@NonNull final String key, @NonNull final Long amount){
        return this.redisTemplate.boundValueOps(key).increment(amount);
    }

    public boolean deleteKey(@NonNull final String key){
        return Objects.isNull(key) || this.redisTemplate.delete(key);
    }
}
