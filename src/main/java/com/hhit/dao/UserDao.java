package com.hhit.dao;

import com.hhit.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface UserDao {
    User selectByusername(String username);//用户查询测试
    User getUserById(String id);//通过用户id获取信息，用于兼职详细信息里边的值的书写
    User getUser(User user);//自己用户的登陆
    User getUserByUserid(String userid);//求职者用户查询，判断是否有值的，后期用于增加或者修该

    User getUser2ByUserid(String userid);//招聘者用户查询，判断是否有值
    User getUser2(User user);//三方登录获取用户信息

    void insertUser(User user);//求职者数据库用户插入
    void updateUser(User user);//求职者数据库用户更新

    void insertUser2(User user);//招聘者数据库用户插入
    void updateUser2(User user);//招聘者数据库用户更新

}
