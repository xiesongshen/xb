package com.xss.service;

import com.xss.dao.LoginDao;
import com.xss.entity.User;
import com.xss.utils.MDUtil;

/**
 * @author XSS
 * @date 2020/6/28
 * @desc
 */
public class LoginService {
    private static LoginDao loginDao = new LoginDao();

    public static User checkLogin(String username, String password) {
        String s = MDUtil.md5(password);
        try {
            return loginDao.checkLogin(username,s);
        } catch (Exception e) {
            return null;
        }
    }

    public static void updatePs(String password,String username){
        loginDao.updatePs(password,username);
    }
}
