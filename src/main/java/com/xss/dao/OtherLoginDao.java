package com.xss.dao;

import com.xss.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 * @author XSS
 * @date 2020/6/30
 * @desc
 */
public class OtherLoginDao extends BaseDao {

    public User findByOpenId(String openid){
        String sql = "select * from user where wx_openid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),openid);
    }
}
