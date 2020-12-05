package com.prmn.todo.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class ToDo implements Serializable {
    private long id;
    private String todoName;
    private String todoContent;
    private Timestamp limitTime;
    private String accountId;
    private String type;
    private Boolean checked = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public String getTodoContent() {
        return todoContent;
    }

    public void setTodoContent(String todoContent) {
        this.todoContent = todoContent;
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

    public Boolean getChecked() {
        return checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}

