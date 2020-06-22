package com.xss.dao;

import com.xss.entity.Menu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author XSS
 * @date 2020/6/22
 * @desc
 */
public class MenuDao extends BaseDao{

    public List<Menu> menuList(){
        String sql = "select * from menu";
        return template.query(sql,new BeanPropertyRowMapper<>(Menu.class));
    }
}
