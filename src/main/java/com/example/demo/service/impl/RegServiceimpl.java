package com.example.demo.service.impl;
import com.example.demo.service.IRegService;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.UserMapper;

@Service
public class RegServiceimpl  implements IRegService {
    @Autowired
    private UserMapper userMapper;
    public User regUser(String userId,String pwd)
    {
        User user = userMapper.loginin(userId,pwd);
        return user;
    }

}