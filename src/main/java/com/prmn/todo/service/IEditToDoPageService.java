package com.prmn.todo.service;

import com.prmn.todo.bean.ToDo;

import java.sql.Timestamp;
import java.util.List;

public interface IEditToDoPageService {

    List<ToDo> showEditToDo(long todoId);

    long updateToDo(String todoName, String todoContent, Timestamp limitTime, String type, long todoId);
}
