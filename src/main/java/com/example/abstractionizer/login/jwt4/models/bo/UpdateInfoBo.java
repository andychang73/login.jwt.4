package com.example.abstractionizer.login.jwt4.models.bo;

import com.example.abstractionizer.login.jwt4.annotations.NullOrNotBlank;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UpdateInfoBo {

    @NullOrNotBlank(message = "can be null but must not be blank")
    private String username;

    @NullOrNotBlank(message = "can be null but must not be blank")
    @Pattern(regexp = "^(.*)@(.*)$", message = "invalid email format")
    private String email;

    @NullOrNotBlank(message = "can be null but must not be blank")
    @Pattern(regexp = "09\\d{8}", message = "invalid phone number")
    private String phone;
}
