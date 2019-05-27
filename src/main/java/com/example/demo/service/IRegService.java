package com.example.demo.service;
import com.example.demo.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface IRegService {
//登陆的验证API

     User regUser(String userId,String pwd);
     //查询余额的API

     int sBalance(String userId);
     //取钱操作的API

     void wBalance(String userId,int money);
     //存钱操作的API

     void dBalance(String userId,int money);
     //转账操作的api可以由上面2个完成，可以看作为A转账给B，A取钱，B存钱
     @Transactional
     void tBalance(String selfId,int s_balance,String othersId,int o_balance);
}
