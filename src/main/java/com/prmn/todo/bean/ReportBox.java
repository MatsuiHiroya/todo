package com.prmn.todo.bean;

import java.sql.Timestamp;

public class ReportBox {
    private long id;
    private long lectureTimeId;
    private String boxName;
    private Timestamp limitTime;
    private Timestamp startTime;
    private String content;

    public ReportBox(long id, long lectureTimeId, String boxName, Timestamp limitTime, Timestamp startTime, String content) {
        this.id = id;
        this.lectureTimeId = lectureTimeId;
        this.boxName = boxName;
        this.limitTime = limitTime;
        this.startTime = startTime;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLectureTimeId() {
        return lectureTimeId;
    }

    public void setLectureTimeId(long lectureTimeId) {
        this.lectureTimeId = lectureTimeId;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public Timestamp getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Timestamp limitTime) {
        this.limitTime = limitTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
