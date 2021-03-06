package com.example.abstractionizer.login.jwt4.db.rmdb.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private Date lastLoginTime;

    private boolean status;
}
