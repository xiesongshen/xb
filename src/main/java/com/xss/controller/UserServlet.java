package com.xss.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("user list");
        req.getRequestDispatcher("/jsp/user/list.jsp").forward(req,resp);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("user add");
    }

    public void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("user del");
    }
}
