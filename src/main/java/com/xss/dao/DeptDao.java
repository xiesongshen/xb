package com.xss.dao;

import com.xss.entity.Dept;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class DeptDao extends BaseDao{

    public List<Dept> listDept(){
        String sql = "select * from dept";
        return template.query(sql,new BeanPropertyRowMapper<>(Dept.class));
    }
}
