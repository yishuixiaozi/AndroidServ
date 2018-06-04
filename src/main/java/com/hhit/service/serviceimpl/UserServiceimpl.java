package com.hhit.service.serviceimpl;

import com.hhit.dao.UserDao;
import com.hhit.model.User;
import com.hhit.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userService")
public class UserServiceimpl implements UserService{

    @Resource
    UserDao userDao;//已经创建了实体类
    @Override
    public User selectByusername(String username) {
        return userDao.selectByusername(username);
    }
    @Override
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }
    @Override
    public User getUser(User user) {
        return userDao.getUser(user);
    }
    @Override
    public User getUser2(User user) {
        return userDao.getUser2(user);
    }
    @Override
    public User getUserByUserid(String userid) {
        return userDao.getUserByUserid(userid);
    }

    @Override
    public User getUser2ByUserid(String userid) {
        return userDao.getUser2ByUserid(userid);
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void insertUser2(User user) {
        System.out.println("------user"+user.toString());
        System.out.println("-------user.getuserid"+user.getUserid());
        System.out.println("-------user.getUSergender"+user.getGender());
        userDao.insertUser2(user);
    }
    @Override
    public void updateUser2(User user) {
        userDao.updateUser2(user);
    }

    @Override
    public List<User> selectAllquser() {
        return userDao.selectAllquser();
    }

    @Override
    public List<User> selectAllfuser() {
        return userDao.selectAllfuser();
    }

    @Override
    public List<User> getAlluser() {
        return userDao.getAlluser();
    }

    @Override
    public void quserdeleteid(int id) {
        userDao.quserdeleteid(id);
    }

    @Override
    public void fuserdeleteid(String userid) {
        userDao.fuserdeleteid(userid);
    }
}
