package com.xss.dao;

import com.xss.utils.DBUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author XSS
 * @date 2020/6/22
 * @desc
 */
public class BaseDao {
    public JdbcTemplate template = new JdbcTemplate(DBUtil.getDateSource());
}
