package com.prmn.todo.bean;

public class CreditStatus {
    private String id;
    private String lectureInformationId;

    public CreditStatus(String id, String lectureInformationId) {
        this.id = id;
        this.lectureInformationId = lectureInformationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLectureInformationId() {
        return lectureInformationId;
    }

    public void setLectureInformationId(String lectureInformationId) {
        this.lectureInformationId = lectureInformationId;
    }
}
