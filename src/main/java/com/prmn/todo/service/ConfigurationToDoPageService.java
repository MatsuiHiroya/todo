package com.prmn.todo.service;

import com.prmn.todo.bean.Notice;
import com.prmn.todo.repository.IConfigurationToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ConfigurationToDoPageService implements IConfigurationToDoPageService{
    private final IConfigurationToDoRepository configurationToDoRepository;

    @Autowired
    public ConfigurationToDoPageService(IConfigurationToDoRepository configurationToDoRepository){
        this.configurationToDoRepository = configurationToDoRepository;
    }

    @Override
    public List<Notice> selectToDoConfig(String accountId){
        List<Notice> toDoConfig = configurationToDoRepository.selectConfigurationToDo(accountId);
        return toDoConfig;
    }

    @Override
    public void updateToDoConfig(boolean toDoConfig, Integer dueDate, String accountId){
        configurationToDoRepository.updateConfigurationToDo(toDoConfig, dueDate, accountId);
    }

}
