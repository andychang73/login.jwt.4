package com.example.abstractionizer.login.jwt4.models.bo;

import com.example.abstractionizer.login.jwt4.annotations.NullOrNotBlank;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserRegisterBo {

    @NotEmpty(message = "must not be null or empty")
    private String username;

    @NotEmpty(message = "must not be null or empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#$%^&*()_=+])(?=\\S+$).{8,}$", message = "invalid password format")
    private String password;

    @NotEmpty(message = "must not be null or empty")
    @Pattern(regexp = "^(.*)@(.*)$", message = "invalid email format")
    private String email;

    @NullOrNotBlank(message = "can be null but not blank")
    @Pattern(regexp = "^09\\d{8}", message = "invalid phone number")
    private String phone;

}
