package com.xss.service;

import com.xss.dao.UserDao;
import com.xss.entity.User;

import java.util.List;

public class UserService {
    private static UserDao userDao = new UserDao();

    public static List<User> listAll(){
        return userDao.listAll();
    }

    public static void addUser(User user){
        userDao.addUser(user);
    }

    public static User findUserByN(String str){
        return userDao.findUserByN(str);
    }
}
