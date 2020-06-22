package com.xss.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author XSS
 * @date 2020/6/22
 * @desc
 */
@WebServlet("/dept/*")
public class DeptServlet extends BaseServlet {
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/dept/dept.jsp").forward(req,resp);
    }
}
