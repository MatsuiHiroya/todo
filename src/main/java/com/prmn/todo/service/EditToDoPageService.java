package com.prmn.todo.service;


import com.prmn.todo.bean.ToDo;
import com.prmn.todo.repository.EditToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class EditToDoPageService implements IEditToDoPageService{

    private EditToDoRepository editToDoRepository;

    @Autowired
    public EditToDoPageService(EditToDoRepository editToDoRepository){this.editToDoRepository = editToDoRepository;}


    @Override
    public List<ToDo> showEditToDo(long todoId) {
        return editToDoRepository.selectToDo(todoId);
    }

    @Override
    public long updateToDo(String todoName, String todoContent, Timestamp limitTime, String type, long todoId){
        return editToDoRepository.updateToDo(todoName, todoContent, limitTime, type, todoId);
    }
}
