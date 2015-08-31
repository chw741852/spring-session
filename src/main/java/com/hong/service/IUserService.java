package com.hong.service;

import com.hong.model.User;

/**
 * Created by cai on 2015/8/31 13:05.
 */
public interface IUserService {
    User getUser(String id);

    User saveUser(User user);

    User updateUser(User user);

    int deleteUser(String id);
}
