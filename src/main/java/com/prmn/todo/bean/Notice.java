package com.prmn.todo.bean;

public class Notice {
    private long id;
    private String accountId;
    private boolean todoConfiguration;
    private long dueDate;

    public Notice(long id, String accountId, boolean todoConfiguration, long dueDate) {
        this.id = id;
        this.accountId = accountId;
        this.todoConfiguration = todoConfiguration;
        this.dueDate = dueDate;
    }

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

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }
}
