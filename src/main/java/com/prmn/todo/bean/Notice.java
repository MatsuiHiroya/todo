package com.prmn.todo.bean;

import java.io.Serializable;

public class Notice implements Serializable {
    private long id;
    private String accountId;
    private boolean todoConfiguration;
    private Integer dueDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public boolean isTodoConfiguration() {
        return todoConfiguration;
    }

    public void setTodoConfiguration(boolean todoConfiguration) {
        this.todoConfiguration = todoConfiguration;
    }

    public Integer getDueDate() {
        return dueDate;
    }

    public void setDueDate(Integer dueDate) {
        this.dueDate = dueDate;
    }
}
