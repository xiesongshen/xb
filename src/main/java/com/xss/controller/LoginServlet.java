package com.xss.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xss.constants.SysConstants;
import com.xss.entity.User;
import com.xss.service.LoginService;
import com.xss.service.UserService;
import com.xss.utils.EmailUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

/**
 * @author XSS
 * @date 2020/6/28
 * @desc
 */
@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        Object o = session.getAttribute(SysConstants.SESSION_IMGCODE);

        ObjectMapper om = new ObjectMapper();

        User user = LoginService.checkLogin(username, password);

        if (user == null || o == null || !(code.equals(o.toString()))) {
            resp.sendRedirect("/index.jsp");
        } else {
            if ("1".equals(remember)) {
                Cookie cookie = new Cookie(SysConstants.COOKIE_USER, URLEncoder.encode(om.writeValueAsString(user), "utf-8"));
                cookie.setMaxAge(7 * 24 * 60 * 60);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
            session.setAttribute(SysConstants.SESSION_LOGIN_CHECK, user);
            req.getRequestDispatcher("/jsp/common/home.jsp").forward(req, resp);
        }
    }


    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map map = req.getParameterMap();
        User user = new User();
        user.setRegisterTime(new Date());

        try {
            BeanUtils.populate(user, map);
        } catch (Exception e) {
        }

        UserService.addUser(user);
        resp.sendRedirect("/index.jsp");
    }

    public void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        try {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = new Cookie(SysConstants.COOKIE_USER, null);
                cookie.setMaxAge(0);

                // 根据你创建cookie的路径进行填写
                cookie.setPath("/");

                resp.addCookie(cookie);
            }
        } catch (Exception ex) {
            return;
        }

        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }


    public void showName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SysConstants.SESSION_LOGIN_CHECK);

        resp.getWriter().write(user.getUsername());
    }

}

