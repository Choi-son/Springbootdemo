package com.example.demo.service.impl;
import com.example.demo.service.IRegService;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.UserMapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegServiceimpl  implements IRegService {
    @Autowired
    private UserMapper userMapper;
    public User regUser(String userId,String pwd)
    {
        User user = userMapper.loginin(userId,pwd);
        return user;
    }

    @Override
    public int sBalance(String userId) {
        int balance = userMapper.sbalance(userId);
        return balance;
    }

    @Override
    public void wBalance(String userId, int money) {
        userMapper.withdraw(userId,money);
    }

    @Override

    public void dBalance(String userId, int money) {
        userMapper.deposit(userId,money);
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void tBalance(String selfId,int s_balance,String othersId,int o_balance)
    {
        userMapper.withdraw(selfId,s_balance);
        userMapper.deposit(othersId,o_balance);
    }
}