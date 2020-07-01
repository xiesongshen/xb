package com.xss.controller;

import com.alibaba.fastjson.JSON;
import com.xss.entity.Dept;
import com.xss.entity.Meeting;
import com.xss.entity.PageCount;
import com.xss.service.DeptService;
import com.xss.service.MeetingService;
import com.xss.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author XSS
 * @date 2020/6/22
 * @desc
 */
@WebServlet("/meeting/*")
public class MeetingServlet extends BaseServlet {


    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageCount p = new PageCount();

        //获取数据数
        Integer count = MeetingService.findPageCount();
        p.setCount(count);

        //总页数
        Integer pageCount = p.getPageCount();

        //获取当前页

        String pageStr = req.getParameter("page");

        PageCount page = MeetingService.meetingList(pageStr);
        req.setAttribute("list", page);


        req.getRequestDispatcher("/jsp/meeting/meetinglist.jsp").forward(req, resp);
    }

    public void meetingInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Meeting meetingInfo = MeetingService.meetingInfo(Integer.valueOf(id));
        req.setAttribute("info", meetingInfo);

        req.getRequestDispatcher("/jsp/meeting/MeetingInfo.jsp").forward(req, resp);
    }

    public void addMeeting(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map map = req.getParameterMap();

        Meeting meeting = new Meeting();
        meeting.setStatus("0");
        meeting.setPublishDate(new Date());

        try {
            BeanUtils.populate(meeting, map);
        } catch (Exception e) {
        }

        MeetingService.addMeeting(meeting);

        resp.sendRedirect("/meeting/list");

    }

}
