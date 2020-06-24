package com.xss.dao;

import com.xss.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class UserDao extends BaseDao{

    public List<User> listAll(){
        String sql = "select * from user";
        return template.query(sql,new BeanPropertyRowMapper<>(User.class));
    }

    public void addUser(User user){
        String sql = "INSERT INTO user(username,password,email,real_name,age,phone,gender,description,register_time,dept_id) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";

        template.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getRealName(),user.getAge(),user.getPhone(),user.getGender(),user.getDescription(),user.getRegisterTime(),user.getDeptId());
    }

    public User findUserByN(String str){
        String sql = "select * from user where username = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), str);
    }
}
