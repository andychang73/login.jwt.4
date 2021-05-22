package com.example.abstractionizer.login.jwt4.models.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class ChangePasswordBo {

    @NotEmpty
    private String oldPassword;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#$%^&*()_=+])(?=\\S+$).{8,}$", message = "invalid password format")
    private String newPassword;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#$%^&*()_=+])(?=\\S+$).{8,}$", message = "invalid password format")
    private String confirmPassword;
}
