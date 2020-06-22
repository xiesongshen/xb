package com.xss.service;

import com.xss.dao.MenuDao;
import com.xss.entity.Menu;

import java.util.List;

/**
 * @author XSS
 * @date 2020/6/22
 * @desc
 */
public class MenuService {
    private static MenuDao menuDao = new MenuDao();

    public static List<Menu> menuList(){
        return menuDao.menuList();
    }
}
