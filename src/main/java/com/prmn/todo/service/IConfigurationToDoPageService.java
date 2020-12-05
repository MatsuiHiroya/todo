package com.prmn.todo.service;

import com.prmn.todo.bean.Notice;

import java.sql.Timestamp;
import java.util.List;

public interface IConfigurationToDoPageService {
    List<Notice> selectToDoConfig(String accountId);
    void updateToDoConfig(boolean toDoConfig, Integer dueDate, String accountId);
}
