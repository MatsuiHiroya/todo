package com.prmn.todo.repository;

import com.prmn.todo.bean.Account;
import com.prmn.todo.bean.Notice;
import com.prmn.todo.bean.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ToDoRepository implements IToDoRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ToDoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ToDo> selectToDoList(String accountId){
        String sql = "select * from TODO where ACCOUNT_ID = ?";
        List<ToDo> toDoList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ToDo.class),accountId);
        for (ToDo toDo : toDoList){
            System.out.println(toDo.getId());
            System.out.println(toDo.getTodoName());
            System.out.println(toDo.getTodoContent());
            System.out.println(toDo.getAccountId());
            System.out.println(toDo.getLimitTime());
            System.out.println(toDo.getType());
        }
        return toDoList;
    }

    @Override
    public void delete(long todoId){
        String sql = "delete from TODO where (ID) = (?)";
        jdbcTemplate.update(sql,todoId);
    }

    @Override
    public List<ToDo> selectConfigTodoList(String accountId, LocalDateTime limitTime, LocalDateTime nowTime) {
        String sql = "select * from TODO where ACCOUNT_ID = ? and LIMIT_TIME <= ? and LIMIT_TIME >= ?";

        List<ToDo> toDoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ToDo.class), accountId, limitTime, nowTime);

        for (ToDo toDo : toDoList) {
            System.out.println(toDo.getId());
            System.out.println(toDo.getTodoName());
            System.out.println(toDo.getAccountId());
            System.out.println(toDo.getLimitTime());
        }



        return toDoList;
    }


}
