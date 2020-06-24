package com.xss.controller;

import com.alibaba.fastjson.JSON;
import com.xss.entity.Dept;
import com.xss.service.DeptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author XSS
 * @date 2020/6/22
 * @desc
 */
@WebServlet("/dept/*")
public class DeptServlet extends BaseServlet {

    public void listDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Dept> depts = DeptService.listDept();
        resp.getWriter().write(JSON.toJSONString(depts));
    }

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/jsp/dept/dept.jsp");
    }

}
