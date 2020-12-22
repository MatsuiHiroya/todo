package com.prmn.todo.repository;

import com.prmn.todo.bean.CreditStatus;
import com.prmn.todo.bean.LectureInfo;
import com.prmn.todo.bean.LectureTime;
import com.prmn.todo.bean.ReportBox;

import java.sql.Timestamp;
import java.util.List;

public interface ICreateToDoRepository {

    public List<CreditStatus> selectCreditStatus(String accountId);

    public List<LectureInfo> selectLectureInfo(String lectureInfoIds);

    public List<ReportBox> selectReportBox(long lectureTimeId);

    public List<LectureTime> selectLectureTime(String lectureId);

    public void insertToDo(String todoName, String todoContent, Timestamp limit, String accountId, String type);
}
