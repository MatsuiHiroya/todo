package com.prmn.todo.repository;

import com.prmn.todo.bean.ToDo;

import java.time.LocalDateTime;
import java.util.List;

public interface IToDoRepository {

    public List<ToDo> selectToDoList(String accountId);

    public void delete(long todoId);

    public List<ToDo> selectConfigTodoList(String accountId, LocalDateTime limitTime, LocalDateTime nowTime);

}
