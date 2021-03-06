package com.hhit.service;

import com.hhit.model.User;

import java.util.List;

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

    List<User> selectAllquser();
    List<User> selectAllfuser();

    List<User> getAlluser();
    void quserdeleteid(int id);
    void fuserdeleteid(String userid);
    void updateQuserinfo(User user);
    void updateFuserinfo(User user);
}
