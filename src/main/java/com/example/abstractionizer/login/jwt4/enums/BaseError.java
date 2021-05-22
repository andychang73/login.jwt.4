package com.example.abstractionizer.login.jwt4.enums;

import org.springframework.http.HttpStatus;

public interface BaseError {
    HttpStatus getHttpStatus();
    String getCode();
    String getMsg();
}
