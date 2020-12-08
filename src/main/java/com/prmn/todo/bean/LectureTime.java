package com.prmn.todo.bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class LectureTime implements Serializable {
    private long id;
    private String lectureInfoId;
    private Date startDate;
    private Time startTime;
    private String startWeek;
    private String times;
    private String LectureInfo;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLectureInfoId() {
        return lectureInfoId;
    }

    public void setLectureInfoId(String lectureInfoId) {
        this.lectureInfoId = lectureInfoId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(String startWeek) {
        this.startWeek = startWeek;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getLectureInfo() {
        return LectureInfo;
    }

    public void setLectureInfo(String lectureInfo) {
        LectureInfo = lectureInfo;
    }
}
