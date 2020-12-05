package com.prmn.todo.repository;

import com.prmn.todo.bean.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConfirmToDoRepository implements IConfirmToDoRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ConfirmToDoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ToDo> selectConfirmToDo(long toDoId, String accountId) {
        String sql = "select * from TODO where ID = ? and ACCOUNT_ID = ?";
        List<ToDo> toDoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ToDo.class), toDoId, accountId);
        return toDoList;
    }

    @Override
    public void DeleteConfirmToDo(long toDoId, String accountId) {
        String sql = "Delete From TODO where ID = ? and ACCOUNT_ID = ?";
        jdbcTemplate.update(sql, toDoId, accountId);
    }

}
