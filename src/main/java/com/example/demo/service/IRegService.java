package com.example.demo.service;
import com.example.demo.entity.User;
public interface IRegService {
//登陆的验证API
     User regUser(String userId,String pwd);
     //查询余额的API
     int sBalance(String userId);
     //取钱操作的API
     void wBalance(String userId,int money);
}
