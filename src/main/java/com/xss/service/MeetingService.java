package com.xss.service;

import com.xss.dao.MeetingDao;
import com.xss.entity.Meeting;

import java.util.List;

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
}
