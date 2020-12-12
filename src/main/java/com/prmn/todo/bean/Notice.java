package com.prmn.todo.bean;

import java.io.Serializable;

public class Notice implements Serializable {
    private long id;
    private String accountId;
    private boolean todoConfig;
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

    public boolean isTodoConfig() {
        return todoConfig;
    }

    public void setTodoConfig(boolean todoConfig) {
        this.todoConfig = todoConfig;
    }

    public Integer getDueDate() {
        return dueDate;
    }

    public void setDueDate(Integer dueDate) {
        this.dueDate = dueDate;
    }
}
