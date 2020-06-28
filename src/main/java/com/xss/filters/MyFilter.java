package com.xss.filters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xss.constants.SysConstants;
import com.xss.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author XSS
 * @date 2020/6/22
 * @desc
 */
@WebFilter("/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        ObjectMapper om = new ObjectMapper();

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        Object o = session.getAttribute(SysConstants.LOGIN_CHECK);
        String uri = req.getRequestURI();


        if (uri.endsWith("/index.jsp")) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (SysConstants.COOKIE_USER.equals(cookie.getName())) {
                        String value = cookie.getValue();

                        //将cookie中的值给进session中
                        value = URLDecoder.decode(value, "utf-8");
                        User user = om.readValue(value, User.class);
                        session.setAttribute(SysConstants.LOGIN_CHECK, user);


                        //如果有cookie.则直接跳转到成功页面
                        filterChain.doFilter(req, resp);
                        req.getRequestDispatcher("/jsp/common/home.jsp").forward(req, resp);
                        return;
                    }
                }
            }

        } else if (uri.endsWith("/login/login") || uri.endsWith("/forget.jsp") || uri.endsWith("/forget/email") || uri.endsWith("/forget/updatePs")) {

        } else {
            if (o == null) {
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }

        filterChain.doFilter(req, resp);


    }

    @Override
    public void destroy() {

    }
}
