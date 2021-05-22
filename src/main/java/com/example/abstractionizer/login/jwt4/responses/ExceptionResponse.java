package com.example.abstractionizer.login.jwt4.responses;

import com.example.abstractionizer.login.jwt4.enums.BaseError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse<T> {
    private String code;
    private String msg;
    private T details;

    public ExceptionResponse(BaseError baseError, T details){
        this.code = baseError.getCode();
        this.msg = baseError.getMsg();
        this.details = details;
    }
}
