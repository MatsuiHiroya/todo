package com.prmn.todo.service;

import com.prmn.todo.bean.ToDo;
import com.prmn.todo.repository.IConfirmToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfirmToDoPageService implements IConfirmToDoPageService {

    private IConfirmToDoRepository confirmToDoRepository;

    @Autowired
    public ConfirmToDoPageService(IConfirmToDoRepository confirmToDoRepository) {
        this.confirmToDoRepository = confirmToDoRepository;
    }

    @Override
    public List<ToDo> getConfirmToDo(long toDoId, String accountId) {
        return confirmToDoRepository.selectConfirmToDo(toDoId, accountId);
    }

    @Override
    public void DeleteConfirmToDo(long toDoId, String accountId) {
        confirmToDoRepository.DeleteConfirmToDo(toDoId, accountId);
    }


}
