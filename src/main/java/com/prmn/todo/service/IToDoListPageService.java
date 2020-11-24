package com.prmn.todo.service;

import com.prmn.todo.bean.ToDo;

import java.util.List;

public interface IToDoListPageService {

    public List<ToDo> selectToDoList();
    //public void selectToDoList();

    public void deleteToDoList(long todoId);
}
