package com.xss.dao;

import com.xss.entity.Meeting;
import com.xss.entity.PageCount;
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

    public List<Meeting> meetingList(PageCount page){
        String sql = "select meeting.id id,dept.name deptName,meeting.title title,meeting.content content,meeting.publish_date publishDate,meeting.start_time startTime,meeting.end_time endTime,meeting.status status,meeting.make_user makeUser from meeting left join dept on meeting.dept_id=dept.id LIMIT ?,?";
        return template.query(sql,new BeanPropertyRowMapper<>(Meeting.class),(page.getPage() - 1) * page.getSize(),page.getSize());
    }

    public Meeting meetingInfo(Integer id){
        String sql = "select meeting.id id,dept.name deptName,meeting.title title,meeting.content content,meeting.publish_date publishDate,meeting.start_time startTime,meeting.end_time endTime,meeting.status status,meeting.make_user makeUser from meeting left join dept on meeting.dept_id=dept.id where meeting.id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Meeting.class),id);
    }

    public void updateStatus(Integer meetingId, Integer status) {
        String sql = "update meeting set status = ? where id = ?";
        template.update(sql, status, meetingId);
    }

    public Integer findPageCount(){
        String sql = "select count(*) from meeting";
        return template.queryForObject(sql,Integer.class);
    }

    public void addMeeting(Meeting meeting){
        String sql = "INSERT into meeting(dept_id,title,content,publish_date,start_time,end_time,status,make_user) VALUES(?,?,?,?,?,?,?,?)";
        template.update(sql,meeting.getDeptId(),meeting.getTitle(),meeting.getContent(),meeting.getPublishDate(),meeting.getStartTime(),meeting.getEndTime(),meeting.getStatus(),meeting.getMakeUser());
    }
}
