package com.xss.controller;

import com.xss.entity.User;
import com.xss.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {


    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserService.listAll();
        req.setAttribute("list",users);
        req.getRequestDispatcher("/jsp/user/list.jsp").forward(req,resp);
    }

    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map map = req.getParameterMap();
        User user = new User();
        user.setRegisterTime(new Date());

        try {
            BeanUtils.populate(user,map);
        } catch (Exception e) {
        }

        UserService.addUser(user);
        resp.sendRedirect("/user/list");
    }

    public void findByN(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        User userByN = UserService.findUserByN(username);

        if (userByN != null){
            resp.getWriter().write("用户名已存在");
        }
    }

    public void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("user del");
    }
}
