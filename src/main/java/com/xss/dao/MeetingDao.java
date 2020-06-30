package com.xss.dao;

import com.xss.entity.Meeting;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author XSS
 * @date 2020/6/30
 * @desc
 */
public class MeetingDao extends BaseDao{

    public List<Meeting> meetingList(){
        String sql = "select meeting.id id,dept.name deptName,meeting.title title,meeting.content content,meeting.publish_date publishDate,meeting.start_time startTime,meeting.end_time endTime,meeting.status status,meeting.make_user makeUser from meeting left join dept on meeting.dept_id=dept.id";
        return template.query(sql,new BeanPropertyRowMapper<>(Meeting.class));
    }
}
