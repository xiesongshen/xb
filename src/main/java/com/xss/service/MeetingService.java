package com.xss.service;

import com.xss.dao.MeetingDao;
import com.xss.entity.Meeting;

import java.util.Date;
import java.util.List;

import com.xss.entity.PageCount;
import com.xss.enums.Sysenum;
import com.xss.utils.DateUtil;
import org.springframework.util.StringUtils;

/**
 * @author XSS
 * @date 2020/6/30
 * @desc
 */
public class MeetingService {
    private static MeetingDao meetingDao = new MeetingDao();

    public static List<Meeting> meetingList(){
        return meetingDao.meetingList();
    }

    public static PageCount meetingList(String pageStr){
        PageCount<Meeting> page = new PageCount<>();

        //当前页
        if (!StringUtils.isEmpty(pageStr)) {
            page.setPage(Integer.valueOf(pageStr));
        }
        //总记录数
        page.setCount(meetingDao.findPageCount());
        //总数据
        List<Meeting> list = meetingDao.meetingList(page);
        page.setData(list);
        return page;
    }

    public static Meeting meetingInfo(Integer id){
        Meeting meeting = meetingDao.meetingInfo(id);
        if ("1".equals(meeting.getStatus())){
            meeting.setStatus("已开始");
        }else if ("2".equals(meeting.getStatus())){
            meeting.setStatus("已结束");
        }else {
            meeting.setStatus("未开始");
        }

        return meeting;
    }

    public static void updateStatusTask() {
        List<Meeting> list = meetingDao.meetingList();
        for (Meeting meeting : list) {
            //当前时间戳
            long currentTime = new Date().getTime();
            long startTime = DateUtil.getTimeByStr(meeting.getStartTime());
            long endTime = DateUtil.getTimeByStr(meeting.getEndTime());

            if (startTime <= currentTime) {
                if (endTime > currentTime) {
                    //会议正在进行中
                    meetingDao.updateStatus(meeting.getId(), Sysenum.DOING.getValue());
                } else {
                    //会议已经结束
                    meetingDao.updateStatus(meeting.getId(), Sysenum.END.getValue());
                }
            } else {
                //会议未开始，不需要处理
            }

        }
    }

    public static Integer findPageCount(){
        return meetingDao.findPageCount();
    }

    public static void addMeeting(Meeting meeting){
        meetingDao.addMeeting(meeting);
    }
}
