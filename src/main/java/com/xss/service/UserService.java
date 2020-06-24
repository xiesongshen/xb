package com.xss.service;

import com.xss.dao.UserDao;
import com.xss.entity.PageCount;
import com.xss.entity.User;
import org.springframework.util.StringUtils;

import java.util.List;

public class UserService {
    private static UserDao userDao = new UserDao();

    public static PageCount listAll(String username,String gender,String deptId,String pageStr){
        PageCount<User> page = new PageCount<>();

        //当前页
        if (!StringUtils.isEmpty(pageStr)) {
            page.setPage(Integer.valueOf(pageStr));
        }
        //总记录数
        page.setCount(userDao.findPageCount(username,gender,deptId));
        //总数据
        List<User> list = userDao.listAll(username,gender,deptId,page);
        page.setData(list);
        return page;
    }

    public static void addUser(User user){
        userDao.addUser(user);
    }

    public static User findUserByN(String str){
        return userDao.findUserByN(str);
    }

    public static void delById(Integer id){
        userDao.delById(id);
    }

    public static User findUserById(Integer id){
        return userDao.findUserById(id);
    }

    public static void updateUser(User user){
        userDao.updateUser(user);
    }

    public static Integer findPageCount(String username,String gender,String deptId){
        return userDao.findPageCount(username,gender,deptId);
    }
}
