package com.prmn.todo.repository;

import com.prmn.todo.bean.Notice;

import java.sql.Timestamp;
import java.util.List;

public interface IConfigurationToDoRepository {
    List<Notice> selectConfigurationToDo(String accountId);
    void updateConfigurationToDo(boolean toDoConfig, Integer dueDate, String accountId);
}
