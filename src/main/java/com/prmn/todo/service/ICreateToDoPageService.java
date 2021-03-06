package com.prmn.todo.service;

import com.prmn.todo.bean.LectureInfo;
import com.prmn.todo.bean.LectureTime;
import com.prmn.todo.bean.ReportBox;

import java.sql.Timestamp;
import java.util.List;

public interface ICreateToDoPageService {

    public List<String> selectCreditStatus(String accountId);

    public List<LectureInfo> selectLectureInfo(String accountId);

    public List<LectureTime> selectLectureTime(String lectureId);

    public List<ReportBox> selectReportBox(long lectureTimeId);

    public boolean insertToDo(String todoName, String todoContent, Timestamp limit, String accountId, String type);
}
