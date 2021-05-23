package com.example.abstractionizer.login.jwt4.db.rmdb.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.abstractionizer.login.jwt4.db.rmdb.entities.User;
import com.example.abstractionizer.login.jwt4.models.bo.UpdateInfoBo;
import com.example.abstractionizer.login.jwt4.models.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    int countByIdOrUsername(@Param("id") Integer id, @Param("username") String username);

    int updateStatus(@Param("id") Integer id, @Param("status") boolean status);

    User selectByIdOrUsername(@Param("id") Integer id, @Param("username") String username);

    boolean getStatus(@Param("id") Integer id);

    int updateUserInfo(@Param("id") Integer id, @Param("user") UpdateInfoBo bo);

    UserInfoVo getByIdOrUsername(@Param("id") Integer id, @Param("username") String username);
}
