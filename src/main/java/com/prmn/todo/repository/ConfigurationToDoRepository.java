package com.prmn.todo.repository;

import com.prmn.todo.bean.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class ConfigurationToDoRepository implements IConfigurationToDoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ConfigurationToDoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Notice> selectConfigurationToDo(String accountId){
        String sql = "select * from NOTICE where ACCOUNT_ID = ?";
        List<Notice> noticeInfo = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Notice.class), accountId);
        return noticeInfo;
    }

    @Override
    public void updateConfigurationToDo(boolean toDoConfig, Integer dueDate, String accountId){
        String sql = "update NOTICE set TODO_CONFIG = ?, DUE_DATE = ? where ACCOUNT_ID = ?";
        jdbcTemplate.update(sql, toDoConfig, dueDate, accountId);
    }
}
