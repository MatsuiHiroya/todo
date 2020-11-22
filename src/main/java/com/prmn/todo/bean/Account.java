package com.prmn.todo.bean;

import java.io.Serializable;

public class Account implements Serializable {
    private String id;
    private String name;
    private String role;
    private String grade;
    private String studentClass;
    private String department;
    private String password;

    public Account(String id, String name, String role, String grade, String studentClass, String department, String password) {
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
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
