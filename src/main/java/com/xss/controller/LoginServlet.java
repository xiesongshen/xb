package com.xss.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xss.constants.SysConstants;
import com.xss.entity.User;
import com.xss.service.LoginService;
import com.xss.service.UserService;
import com.xss.utils.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

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
        HttpSession session = req.getSession();
        ObjectMapper om = new ObjectMapper();


        User user = LoginService.checkLogin(username, password);

        if (user == null) {
            resp.sendRedirect("/index.jsp");
        } else {
            if ("1".equals(remember)) {
                Cookie cookie = new Cookie(SysConstants.COOKIE_USER, URLEncoder.encode(om.writeValueAsString(user), "utf-8"));
                cookie.setMaxAge(7 * 24 * 60 * 60);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
            session.setAttribute(SysConstants.LOGIN_CHECK, user);
            req.getRequestDispatcher("/jsp/common/home.jsp").forward(req, resp);
        }
    }

}

