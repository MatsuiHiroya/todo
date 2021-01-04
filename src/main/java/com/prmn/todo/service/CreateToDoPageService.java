package com.prmn.todo.service;

import com.prmn.todo.bean.LectureInfo;
import com.prmn.todo.bean.LectureTime;
import com.prmn.todo.bean.ReportBox;
import com.prmn.todo.repository.ICreateToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CreateToDoPageService implements ICreateToDoPageService{

    private final ICreateToDoRepository createToDoRepository;

    @Autowired
    public CreateToDoPageService(ICreateToDoRepository createToDoRepository){
        this.createToDoRepository = createToDoRepository;
    }

    @Override
    public List<String> selectCreditStatus(String accountId){
        List<String> lectureIds = new ArrayList<>();
        createToDoRepository.selectCreditStatus(accountId).forEach(s -> lectureIds.add(s.getLectureInfoId()));
        return lectureIds;
    }

    @Override
    public List<LectureInfo> selectLectureInfo(String accountId){
        List<LectureInfo> lectureInformationList = new ArrayList<>();
        selectCreditStatus(accountId).forEach(s -> {
            LectureInfo lectureInformation = createToDoRepository.selectLectureInfo(s).get(0);
            lectureInformationList.add(lectureInformation);}
            );
        return lectureInformationList;
    }

    @Override
    public List<LectureTime> selectLectureTime(String lectureId){
        return createToDoRepository.selectLectureTime(lectureId);
    }

    @Override
    public List<ReportBox> selectReportBox(long lectureTimeId){
        return createToDoRepository.selectReportBox(lectureTimeId);
    }

    @Override
    public boolean insertToDo(String todoName, String todoContent, Timestamp limit,String accountId,String type){
        return createToDoRepository.insertToDo(todoName,todoContent,limit,accountId,type);
    }

}
