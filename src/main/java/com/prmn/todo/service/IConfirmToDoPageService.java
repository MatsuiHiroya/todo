package com.prmn.todo.service;

import com.prmn.todo.bean.ToDo;

import java.util.List;

public interface IConfirmToDoPageService {

    /*
    ToDoの詳細を持ってくる
    @return List<ToDo>
     */
    public List<ToDo> getConfirmToDo(long toDoId, String accountId);

    /*
    表示しているToDoを削除する
     */
    public void DeleteConfirmToDo(long toDoId, String accountId);

}
