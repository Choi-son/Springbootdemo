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
  //转账业务
 // int transfer(@Param("userId")String UserId,@Param("transferBalance") String balance);
  //存取业务
 // int deposit(@Param("balance") String balance);
  //int take(@Param("balance")String balance);
}