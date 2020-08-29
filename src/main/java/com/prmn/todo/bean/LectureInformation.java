package com.prmn.todo.bean;

public class LectureInformation {
    private String id;
    private String lectureName;
    private String responsible;
    private String department;
    private long grade;
    private String season;
    private String type;

    public LectureInformation(String id, String lectureName, String responsible,
                              String department, long grade, String season, String type) {
        this.id = id;
        this.lectureName = lectureName;
        this.responsible = responsible;
        this.department = department;
        this.grade = grade;
        this.season = season;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
