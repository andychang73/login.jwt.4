package com.example.abstractionizer.login.jwt4.models.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {
    private Integer userId;
    private String username;
    private String email;
    private String phone;
}
