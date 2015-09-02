package com.hong.service.impl;

import com.hong.model.User;
import com.hong.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by cai on 2015/8/31 13:05.
 */
@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Cacheable(value = "user", key = "#id")
    @Override
    public User getUser(String id) {
        User user = new User();
        user.setId("0");
        user.setUsername("guest");
        user.setPassword("guest");
        user.setName("guest");
        user.setAge(24);
        user.setSex("男");

        logger.debug("getUser");

        return user;
    }

    @CachePut(value = "user", key = "#user.id")
    @Override
    public User saveUser(User user) {
        logger.debug("saveUser");

        return user;
    }

    @CachePut(value = "user", key = "#user.id")
    @Override
    public User updateUser(User user) {
        logger.debug("updateUser");

        return user;
    }

    @CacheEvict(value = "user", key = "#id")
    @Override
    public int deleteUser(String id) {
        logger.debug("deleteUser");

        return 1;
    }
}
