package com.prmn.todo.repository;

import com.prmn.todo.bean.ToDo;

import java.util.List;

public interface IConfirmToDoRepository {

    /*
    ToDoテーブルからToDoの詳細を持ってくる。
    @return List<ToDo>
     */
    public List<ToDo> selectConfirmToDo(long toDoId, String accountId);

    /*
    指定したtoDoIDと学籍番号のToDoのデータをToDoテーブルから削除する。
     */
    public void DeleteConfirmToDo(long toDoId, String accountId);

}
