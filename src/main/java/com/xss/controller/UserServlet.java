package com.xss.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xss.constants.SysConstants;
import com.xss.entity.Dept;
import com.xss.entity.Meeting;
import com.xss.entity.PageCount;
import com.xss.entity.User;
import com.xss.service.DeptService;
import com.xss.service.UserService;
import com.xss.utils.MDUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {


    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String gender = req.getParameter("gender");
        String deptId = req.getParameter("deptId");
        PageCount p = new PageCount();


        if (username == null) {
            username = "";
        }

        if (gender == null) {
            gender = "-1";
        }

        if (deptId == null) {
            deptId = "-1";
        }

        //获取数据数
        Integer count = UserService.findPageCount(username, gender, deptId);
        p.setCount(count);

        //总页数
        Integer pageCount = p.getPageCount();

        //获取当前页
        String pageStr = req.getParameter("page");


        PageCount page = UserService.listAll(username, gender, deptId, pageStr);
        req.setAttribute("username", username);
        req.setAttribute("gender", gender);
        req.setAttribute("deptId", deptId);
        req.setAttribute("list", page);


        req.getRequestDispatcher("/jsp/user/list.jsp").forward(req, resp);
    }

    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map map = req.getParameterMap();
        User user = new User();
        user.setRegisterTime(new Date());

        try {
            BeanUtils.populate(user, map);
        } catch (Exception e) {
        }

        UserService.addUser(user);
        resp.sendRedirect("/user/list");
    }

    public void findByN(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        User userByN = UserService.findUserByN(username);

        if (userByN != null) {
            resp.getWriter().write("1");
        } else {
            resp.getWriter().write("0");
        }
    }

    public void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        System.out.println(id);
        UserService.delById(Integer.valueOf(id));
        resp.sendRedirect("/user/list");
    }

    public void toUpdateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        User user = UserService.findUserById(Integer.valueOf(id));
        List<Dept> depts = DeptService.listDept();

        req.setAttribute("dept", depts);
        req.setAttribute("user", user);

        req.getRequestDispatcher("/jsp/user/updateUser.jsp").forward(req, resp);
    }

    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map map = req.getParameterMap();

        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        user.setPassword(MDUtil.md5(user.getPassword()));

        UserService.updateUser(user);
        resp.sendRedirect("/user/list");
    }

    public void findUserByDeptId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptId = req.getParameter("deptId");
        List<User> list = UserService.findUserByDeptId(Integer.valueOf(deptId));

        PrintWriter pw = resp.getWriter();
        /*ObjectMapper om = new ObjectMapper();*/
        pw.write(JSON.toJSONString(list));

    }

}
