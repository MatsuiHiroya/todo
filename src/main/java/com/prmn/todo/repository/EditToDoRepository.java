package com.prmn.todo.repository;

import com.prmn.todo.bean.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class EditToDoRepository implements IEditToDoRepository{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EditToDoRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    @Override
    public List<ToDo> selectToDoList(long id){
        var sql = "select * from todo where id = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ToDo.class),id);
    }

    @Override
    public long updateToDo(String todoName, String todoContent, Timestamp limitTime, String type, long id){
        var sql = "update todo set todo_name = ?,todo_content = ?,limit_time = ?,type = ? where id = ?";
        return jdbcTemplate.update(sql,todoName,todoContent,limitTime,type,id);
    }
}
