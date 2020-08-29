package com.prmn.todo.bean;

public class Account {
    private String id;
    private String name;
    private String role;
    private long grade;
    private String studentClass;
    private String department;
    private String password;

    public Account(String id, String name, String role, long grade, String studentClass, String department, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.grade = grade;
        this.studentClass = studentClass;
        this.department = department;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
