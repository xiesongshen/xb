package com.xss.dao;

import com.xss.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 * @author XSS
 * @date 2020/6/28
 * @desc
 */
public class LoginDao extends BaseDao {

    public User checkLogin(String username, String password) {
        String sql = "select * from user where username=? and password=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username,password);
    }

    public void updatePs(String password,String username){
        String sql = "update user set password = ? where username=?";
        template.update(sql,password,username);
    }
}

