package com.xss.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author XSS
 * @date 2020/6/22
 * @desc
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String[] split = uri.split("/");
        String method = split[split.length - 1];


       /* UserServlet userServlet = new UserServlet();
        Class<? extends UserServlet> aClass = userServlet.getClass();*/

       //此处的this指谁调用service，this就是谁
        Class aClass = this.getClass();
        try {
            Method m = aClass.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            m.setAccessible(true);
            m.invoke(this,req,resp);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
