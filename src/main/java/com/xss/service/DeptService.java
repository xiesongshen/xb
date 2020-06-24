package com.xss.service;

import com.xss.dao.DeptDao;
import com.xss.entity.Dept;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class DeptService {
    private static DeptDao deptDao= new DeptDao();

    public static List<Dept> listDept(){
      return deptDao.listDept();
    }
}
