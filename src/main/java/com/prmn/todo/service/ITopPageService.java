package com.prmn.todo.service;

import com.prmn.todo.bean.Notice;
import com.prmn.todo.bean.ToDo;

import java.time.LocalDateTime;
import java.util.List;

public interface ITopPageService {

    /*
    /todoRepositoryからログイン中の学籍番号の生徒のTodoリストの一覧を取得する。
    /@return List<ToDo>
     */
    public List<ToDo> selectConfigToDoList(String accountId, LocalDateTime limitTime, LocalDateTime nowTime);

    /*
    /ログイン中の生徒の学籍番号を元に通知の設定情報を取得する
    /@return List<Notice>
     */
    public List<Notice> selectToDoConfig(String accountId);

}
