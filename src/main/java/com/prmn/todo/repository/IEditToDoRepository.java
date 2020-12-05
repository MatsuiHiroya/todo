package com.prmn.todo.repository;

import com.prmn.todo.bean.ToDo;

import java.sql.Timestamp;
import java.util.List;

public interface IEditToDoRepository {

    List<ToDo> selectToDoList(long id);

    long updateToDo(String todoName, String todoContent, Timestamp limitTime,String type,long id);
}
