package com.prmn.todo.bean;

import java.sql.Timestamp;

public class ToDo {
    private long id;
    private String toDoName;
    private String toDoContent;
    private Timestamp limitTime;
    private String accountId;
    private String type;

    public ToDo(long id, String toDoName, String toDoContent, Timestamp limitTime, String accountId, String type) {
        this.id = id;
        this.toDoName = toDoName;
        this.toDoContent = toDoContent;
        this.limitTime = limitTime;
        this.accountId = accountId;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToDoName() {
        return toDoName;
    }

    public void setToDoName(String toDoName) {
        this.toDoName = toDoName;
    }

    public String getToDoContent() {
        return toDoContent;
    }

    public void setToDoContent(String toDoContent) {
        this.toDoContent = toDoContent;
    }

    public Timestamp getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Timestamp limitTime) {
        this.limitTime = limitTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

