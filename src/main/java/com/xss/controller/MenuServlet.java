package com.xss.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xss.entity.Menu;
import com.xss.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author XSS
 * @date 2020/6/22
 * @desc
 */
@WebServlet("/menuServlet")
public class MenuServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Menu> list = MenuService.menuList();
        ArrayList<Menu> parent = new ArrayList<>();
        ArrayList<Menu> children = new ArrayList<>();

        for (Menu m : list) {
            if (m.getType() == 1){
                parent.add(m);
            }
            if (m.getType() == 2){
                children.add(m);
            }
        }

        HashMap<String, List<Menu>> map = new HashMap<>();
        map.put("parent",parent);
        map.put("children",children);

        ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(map);

        PrintWriter pw = resp.getWriter();
        pw.write(s);
        pw.close();
    }
}
