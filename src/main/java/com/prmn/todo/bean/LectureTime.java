package com.prmn.todo.bean;

import java.security.Timestamp;
import java.util.Date;

public class LectureTime {
    private long id;
    private String lectureInformationId;
    private String startDate;
    private String startTime;
    private String startWeek;
    private long times;
    private String LectureInformation;

    public LectureTime(long id, String lectureInformationId, String startDate,
                       String startTime, String startWeek, long times, String lectureInformation) {
        this.id = id;
        this.lectureInformationId = lectureInformationId;
        this.startDate = startDate;
        this.startTime = startTime;
        this.startWeek = startWeek;
        this.times = times;
        LectureInformation = lectureInformation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLectureInformationId() {
        return lectureInformationId;
    }

    public void setLectureInformationId(String lectureInformationId) {
        this.lectureInformationId = lectureInformationId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(String startWeek) {
        this.startWeek = startWeek;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public String getLectureInformation() {
        return LectureInformation;
    }

    public void setLectureInformation(String lectureInformation) {
        LectureInformation = lectureInformation;
    }
}
