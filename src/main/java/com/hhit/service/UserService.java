package com.hhit.service;

import com.hhit.model.User;

public interface UserService {
    User selectByusername(String username);
    User getUserById(String id);
    User getUser(User user);
    User getUser2(User user);
    User getUserByUserid(String userid);//判断用户是否存在的
    User getUser2ByUserid(String userid);//判断招聘者用户是否存在

    void insertUser(User user);
    void updateUser(User user);
    void insertUser2(User user);
    void updateUser2(User user);
}
