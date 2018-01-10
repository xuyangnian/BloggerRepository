package com.fnd.blogger.manager.service.impl;

import com.fnd.blogger.manager.entity.User;
import com.fnd.blogger.manager.repository.UserRepository;
import com.fnd.blogger.manager.service.UserService;
import com.fnd.blogger.manager.utils.JacksonUtil;
import com.fnd.blogger.manager.utils.RedisUtil;
import com.fnd.blogger.manager.utils.tokenUtil.TokenGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenGenerateUtil tokenGenerateUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Cacheable(value = "user1",key = "#userName")
    @Override
    public String login(String userName, String password) {
        String token=null;
        User user=userRepository.findByNameAndPassword(userName,password);
        if(user!=null){
            token=tokenGenerateUtil.getToken();
            /*String u= JacksonUtil.obj2json(user);
            redisUtil.set(token,u);
            redisUtil.setKeyExpireTime(token,3);
            redisUtil.set(u,token);
            redisUtil.setKeyExpireTime(u,3);*/
        }
        return token;
    }
}
