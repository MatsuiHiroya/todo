package com.prmn.todo.repository;

import com.prmn.todo.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CreateToDoRepository implements ICreateToDoRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CreateToDoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CreditStatus> selectCreditStatus(String accountId){
        String sql = "select LECTURE_INFO_ID from CREDIT_STATUS where ID = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(CreditStatus.class),accountId);
    }

    @Override
    public List<LectureInfo> selectLectureInfo(String lectureId){
        String sql = "select * from LECTURE_INFO where ID = ?";
        return  jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(LectureInfo.class),lectureId);
    }

    @Override
    public List<LectureTime> selectLectureTime(String lectureId){
        String sql = "select * from LECTURE_TIME where LECTURE_INFO_ID = ?";
        return  jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(LectureTime.class),lectureId);
    }

    @Override
    public List<ReportBox> selectReportBox(long lectureTimeId){
        String sql = "select * from REPORT_BOX where LECTURE_TIME_ID = ?";
        return  jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ReportBox.class),lectureTimeId);
    }

    @Override
    public void insertToDo(String todoName, String todoContent, Timestamp limit, String accountId, String type){
        String sql = "insert into TODO(todo_name, todo_content, limit_time, account_id, type) values(?,?,?,?,?)";
        jdbcTemplate.update(sql,todoName,todoContent,limit,accountId,type);
    }

    /**
    @Override
    public List<ReportBox>  (String reportBoxName){
        String sql = "select * from REPORT_BOX where LECTURE_TIME_ID = ?";
        return  jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ReportBox.class),reportBoxName);
    }*/
}
