package com.prmn.todo.service;

import com.prmn.todo.bean.Notice;
import com.prmn.todo.bean.ToDo;
import com.prmn.todo.repository.IConfigurationToDoRepository;
import com.prmn.todo.repository.IToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopPageService implements ITopPageService{

    private IToDoRepository toDoRepository;
    private IConfigurationToDoRepository configurationToDoRepository;

    @Autowired
    public TopPageService (IToDoRepository toDoRepository, IConfigurationToDoRepository configurationToDoRepository) {
        this.toDoRepository = toDoRepository;
        this.configurationToDoRepository = configurationToDoRepository;
    }

    @Override
    public List<ToDo> selectConfigToDoList(String accountId, LocalDateTime limitTime, LocalDateTime nowTime) {
        return toDoRepository.selectConfigTodoList(accountId, limitTime, nowTime);
    }

    //通知情報の取得
    @Override
    public List<Notice> selectToDoConfig(String accountId) {
        return configurationToDoRepository.selectConfigurationToDo(accountId);
    }

}
