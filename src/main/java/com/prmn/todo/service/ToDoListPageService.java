package com.prmn.todo.service;

import com.prmn.todo.bean.ToDo;
import com.prmn.todo.repository.IToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListPageService implements IToDoListPageService{

    private final IToDoRepository toDoRepository;

    @Autowired
    public ToDoListPageService(IToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

    @Override
    public List<ToDo> selectToDoList(){
        //仮Idの設定
        String account_id = "b2182290";
        List<ToDo> toDoList = toDoRepository.selectToDoList(account_id);
        return toDoList;
    }

    /**@Override
    public void selectToDoList(){
        //仮Idの設定
        String account_id = "b2182290";
        System.out.println("ppppppppp");
        toDoRepository.selectToDoList(account_id);
        return;
    }*/
}
