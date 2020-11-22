package com.prmn.todo.repository;

import com.prmn.todo.bean.ToDo;

import java.util.List;

public interface IToDoRepository {

    public List<ToDo> selectToDoList(String accountId);

}
