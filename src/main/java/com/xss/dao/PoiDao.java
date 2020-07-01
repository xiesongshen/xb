package com.xss.dao;

import com.xss.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author XSS
 * @date 2020/7/1
 * @desc
 */
public class PoiDao extends BaseDao {

    public List<User> UserExcel(String username, String gender, String deptId) {
        if ("-1".equals(gender) && "-1".equals(deptId)) {
            String sql = "select user.username username,dept.name deptName,user.real_name realName,user.gender gender," +
                    "user.email email,user.phone phone from user left join dept on user.dept_id=dept.id where user.username like ?";
            return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + username + "%");
        } else if ("-1".equals(gender)) {
            String sql = "select user.username username,dept.name deptName,user.real_name realName,user.gender gender," +
                    "user.email email,user.phone phone from user left join dept on user.dept_id=dept.id where user.username like ? and user.dept_id=?";
            return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + username + "%", Integer.valueOf(deptId));
        } else if ("-1".equals(deptId)) {
            String sql = "select user.username username,dept.name deptName,user.real_name realName,user.gender gender," +
                    "user.email email,user.phone phone from user left join dept on user.dept_id=dept.id where user.username like ? and user.gender=?";
            return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + username + "%", gender);
        } else {
            String sql = "select user.username username,dept.name deptName,user.real_name realName,user.gender gender," +
                    "user.email email,user.phone phone from user left join dept on user.dept_id=dept.id where user.username like ? and user.gender=? and user.dept_id=?";
            return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + username + "%", gender, Integer.valueOf(deptId));
        }
    }
}
