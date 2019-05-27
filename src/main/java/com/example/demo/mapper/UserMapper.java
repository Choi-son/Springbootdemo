package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
 //登陆验证查询
  User loginin(@Param("u_id") String UserId,@Param("password") String pwd);
 //查询余额操作
  int  sbalance(@Param("u_id") String UserId);
//取钱操作
  void withdraw(@Param("u_id") String UserId,@Param("money") int money);
  //存钱操作
 void deposit(@Param("u_id") String UserId,@Param("money") int money);
}