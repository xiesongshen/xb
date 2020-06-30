package com.xss.controller;

import com.alibaba.fastjson.JSON;
import com.xss.entity.Dept;
import com.xss.entity.Meeting;
import com.xss.service.DeptService;
import com.xss.service.MeetingService;

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
@WebServlet("/meeting/*")
public class MeetingServlet extends BaseServlet {


    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Meeting> meetingList = MeetingService.meetingList();
        req.setAttribute("list",meetingList);


        req.getRequestDispatcher("/jsp/meeting/mettinglist.jsp").forward(req,resp);
    }

}
