package com.example.abstractionizer.login.jwt4.responses;

public class SuccessResponse<T> {
    T data;

    public SuccessResponse(){
        this.data = (T)"Success";
    }

    public SuccessResponse(T data){
        this.data = data;
    }

    public SuccessResponse setData(T data){
        this.data = data;
        return this;
    }

    public T getData(){
        return this.data;
    }
}
