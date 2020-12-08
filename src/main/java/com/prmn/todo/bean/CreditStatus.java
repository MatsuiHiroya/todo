package com.prmn.todo.bean;

import java.io.Serializable;

public class CreditStatus implements Serializable {
    private String id;
    private String lectureInfoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLectureInfoId() {
        return lectureInfoId;
    }

    public void setLectureInfoId(String lectureInformationId) {
        this.lectureInfoId = lectureInformationId;
    }
}
