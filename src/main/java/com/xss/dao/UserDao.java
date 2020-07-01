package com.xss.dao;

import com.xss.entity.Meeting;
import com.xss.entity.PageCount;
import com.xss.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class UserDao extends BaseDao {

    public List<User> listAll(String username, String gender, String deptId, PageCount page) {
        if ("-1".equals(gender) && "-1".equals(deptId)) {
            String sql = "select user.id id,user.username,user.email email,user.real_name realName,user.age age,user.phone phone,user.gender gender,user.register_time registerTime,dept.name deptName from user LEFT JOIN dept on user.dept_id=dept.id where username like ? LIMIT ?,?";
            return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + username + "%",(page.getPage() - 1) * page.getSize(),page.getSize());
        } else if ("-1".equals(deptId)) {
            String sql = "select user.id id,user.username,user.email email,user.real_name realName,user.age age,user.phone phone,user.gender gender,user.register_time registerTime,dept.name deptName from user LEFT JOIN dept on user.dept_id=dept.id where username like ? and gender=? LIMIT ?,?";
            return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + username + "%", Integer.valueOf(gender),(page.getPage() - 1) * page.getSize(),page.getSize());
        } else if ("-1".equals(gender)) {
            String sql = "select user.id id,user.username,user.email email,user.real_name realName,user.age age,user.phone phone,user.gender gender,user.register_time registerTime,dept.name deptName from user LEFT JOIN dept on user.dept_id=dept.id where username like ? and dept_id=? LIMIT ?,?";
            return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + username + "%", Integer.valueOf(deptId),(page.getPage() - 1) * page.getSize(),page.getSize());
        } else {
            String sql = "select user.id id,user.username,user.email email,user.real_name realName,user.age age,user.phone phone,user.gender gender,user.register_time registerTime,dept.name deptName from user LEFT JOIN dept on user.dept_id=dept.id where username like ? and dept_id=? and gender=? LIMIT ?,?";
            return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + username + "%", Integer.valueOf(deptId), Integer.valueOf(gender),(page.getPage() - 1) * page.getSize(),page.getSize());
        }

    }

    public Integer findPageCount(String username,String gender,String deptId){
        if ("-1".equals(gender) && "-1".equals(deptId)) {
            String sql = "select count(*) from user where username like ?";
            return template.queryForObject(sql,Integer.class,"%" + username + "%");
        } else if ("-1".equals(deptId)) {
            String sql = "select count(*) from user where username like ? and gender=?";
            return template.queryForObject(sql,Integer.class,"%" + username + "%",Integer.valueOf(gender));
        } else if ("-1".equals(gender)) {
            String sql = "select count(*) from user where username like ? and dept_id=?";
            return template.queryForObject(sql,Integer.class,"%" + username + "%",Integer.valueOf(deptId));
        } else {
            String sql = "select count(*) from user where username like ? and gender=? and dept_id=?";
            return template.queryForObject(sql,Integer.class,"%" + username + "%",Integer.valueOf(gender),Integer.valueOf(deptId));
        }
    }

    public void addUser(User user) {
        String sql = "INSERT INTO user(username,password,email,real_name,age,phone,gender,description,register_time,pic,dept_id,wx_openid) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        template.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getRealName(), user.getAge(), user.getPhone(), user.getGender(), user.getDescription(), user.getRegisterTime(),user.getPic(), user.getDeptId(),user.getWxOpenid());
    }


    public User findUserByN(String str) {
        String sql = "select * from user where username = ?";
        try {
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),str);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void delById(Integer id) {
        String sql = "delete from user where id = ?";
        template.update(sql, id);
    }

    public User findUserById(Integer id) {
        String sql = "select * from user where id =?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    public void updateUser(User user) {
        String sql = "update user set username=?,password=?,email=?,real_name=?,age=?,phone=?,gender=?,description=?,dept_id=? where id=? ";
        template.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getRealName(), user.getAge(), user.getPhone(), user.getGender(), user.getDescription(), user.getDeptId(), user.getId());
    }

    public void updateHeadImg(String pic,Integer id) {
        String sql= "update user set pic=? where id=?";
        template.update(sql,pic,id);
    }

    public List<User> findUserByDeptId(Integer deptId){
        String sql = "select * from user where dept_id=?";
        return template.query(sql,new BeanPropertyRowMapper<>(User.class),deptId);
    }

}
