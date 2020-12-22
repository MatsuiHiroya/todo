package com.prmn.todo.page;

import com.prmn.todo.bean.LectureInfo;
import com.prmn.todo.bean.LectureTime;
import com.prmn.todo.bean.ReportBox;
import com.prmn.todo.service.ICreateToDoPageService;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.jwicket.ui.datepicker.DatePicker;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;

//import org.apache.wicket.extensions.markup.html.form.DateTextField;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@MountPath("CreateToDo")
public class CreateToDoPage extends WebPage {

    //private List<LectureTime> lectureTimeList;
    //private List<ReportBox> reportBoxList = new ArrayList<>();
    private Date limitDate;
    //private IModel dateModel = Model.of(new Date());
    private String limitHour;
    private String limitMinute;
    private String todoType;
    private String reportBoxName;
    private String reportBoxContent;
    //private ReportBox selectedReportBox;

    @SpringBean
    private ICreateToDoPageService createToDoPageService;

    public CreateToDoPage(Date limitDate1,String limitHour1,String limitMinute1,String todoType1,String reportBoxName1,String reportBoxContent1) {
        this.limitDate = limitDate1;
        this.limitHour = limitHour1;
        this.limitMinute = limitMinute1;
        this.todoType = todoType1;
        this.reportBoxName = reportBoxName1;
        this.reportBoxContent = reportBoxContent1;

        var toTopLink = new BookmarkablePageLink<>("toTopPage",TopPage.class);
        add(toTopLink);
        var toToDoListLink = new BookmarkablePageLink<>("toToDoListPage",ToDoListPage.class);
        add(toToDoListLink);
        var toConfigurationToDoLink = new BookmarkablePageLink<>("toConfigurationToDoPage",ConfigurationToDoPage.class);
        add(toConfigurationToDoLink);

        IModel dateModel = Model.of(limitDate);
        DateTextField date = new DateTextField("date", dateModel);


        List<String> limitHourList = new ArrayList<>();
        for(int i=0;i<24;i++)limitHourList.add(Integer.toString(i));
        List<String> limitMinuteList = new ArrayList<>();
        for(int i=0;i<60;i+=5)limitMinuteList.add(Integer.toString(i));
        var limitHourModel = new Model<>(limitHour);
        var limitMinuteModel = new Model<>(limitMinute);
        var limitHourDropDown = new DropDownChoice<>("limitHour",limitHourModel,limitHourList);
        limitHourDropDown.setOutputMarkupId(true);
        var limitMinuteDropDown = new DropDownChoice<>("limitMinute",limitMinuteModel,limitMinuteList);
        limitMinuteDropDown.setOutputMarkupId(true);

        IModel todoTypeModel = Model.of(todoType);
        List<String> todoTypeList = Arrays.asList("講義","レポートボックス","その他");
        var todoTypeDropDown = new DropDownChoice<>("todoTypeDropdown",todoTypeModel ,todoTypeList);
        todoTypeDropDown.setOutputMarkupId(true);

        var textModel = Model.of(reportBoxName);
        var todoNameText = new TextField("todoNameTextBox", textModel);

        todoNameText.setOutputMarkupId(true);

        var contentModel = Model.of(reportBoxContent);
        var todoContentText = new TextArea<>("todoContentTextBox",contentModel);

        todoContentText.setOutputMarkupId(true);



        var createToDoForm = new Form<>("createToDoForm"){
            @Override
            protected void onSubmit(){
                /*System.out.println("aaaaaaaa");
                System.out.println(date.getModelObject());
                System.out.println(date.getModel());
                System.out.println(todoNameText.getModelObject());
                System.out.println("b");
                System.out.println(todoContentText.getModelObject());
                System.out.println(todoTypeDropDown.getModelObject());
                String limitDateToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date.getModelObject()).substring(0,10);
                System.out.println(limitDateToString);
                String limit = limitDateToString + " " + limitHourDropDown.getModelObject() + ":" + limitMinuteDropDown.getModelObject() + ":00";
                try {
                    Timestamp ts = new Timestamp(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(limit).getTime());
                    System.out.println(ts);
                    createToDoPageService.insertToDo(todoNameText.getModelObject().toString(),todoContentText.getModelObject(),ts,"b2182290",todoTypeDropDown.getModelObject().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/
            }
        };
        add(createToDoForm);

        List<String> lectureNameDropdown = new ArrayList<>(){};
        List<LectureInfo> lectureInfoList= createToDoPageService.selectLectureInfo("b2182290");
        lectureInfoList.forEach(lectureInfo ->
            lectureNameDropdown.add(lectureInfo.getLectureName())
        );

        List<String> lectureTimeDropdown = new ArrayList<>(){};
        lectureTimeDropdown.add("講義名を選択");
        List<String> reportBoxDropdown = new ArrayList<>(){};
        reportBoxDropdown.add("講義名を選択");

        DropDownChoice dropDownChoice1 = new DropDownChoice<>("lectureNameDropdown", new Model<>(),lectureNameDropdown);
        DropDownChoice dropDownChoice2 = new DropDownChoice<>("lectureTimeDropdown", new Model<>("講義名を選択"),lectureTimeDropdown);
        DropDownChoice dropDownChoice3 = new DropDownChoice<>("reportBoxDropdown", new Model<>("講義名を選択"),reportBoxDropdown);
        dropDownChoice2.setOutputMarkupId(true);
        dropDownChoice3.setOutputMarkupId(true);

        dropDownChoice1.add(new AjaxFormComponentUpdatingBehavior("change"){
            protected void onUpdate(AjaxRequestTarget target) {

                lectureTimeDropdown.clear();
                //reportBoxDropdown.clear();

                lectureInfoList.stream()
                        .filter(lectureInfo -> lectureInfo.getLectureName().equals(dropDownChoice1.getModelObject()))
                        .forEach(lectureInfo -> {
                            List<LectureTime> lectureTimeList = createToDoPageService.selectLectureTime(lectureInfo.getId());
                            lectureTimeList.stream().forEach(lectureTime ->
                                lectureTimeDropdown.add(lectureTime.getTimes())
                            );
                        });

                //dropDownChoice2.setChoices(lectureTimeDropdown);
                //dropDownChoice3.setChoices(reportBoxDropdown);
                //target.add(dropDownChoice2,dropDownChoice3);
                target.add(dropDownChoice2);
            }
        });


        this.add(new FeedbackPanel("feedBack"));
        createToDoForm.add(date);
        date.add(new DatePicker());
        date.setOutputMarkupId(true);


        dropDownChoice2.add(new AjaxFormComponentUpdatingBehavior("change"){
            protected void onUpdate(AjaxRequestTarget target) {
                reportBoxDropdown.clear();

                lectureInfoList.stream()
                        .filter(lectureInfo -> lectureInfo.getLectureName().equals(dropDownChoice1.getModelObject()))
                        .forEach(lectureInfo -> {
                            List<LectureTime> lectureTimeList = createToDoPageService.selectLectureTime(lectureInfo.getId());
                            lectureTimeList.stream()
                                    .filter(lectureTime -> lectureTime.getTimes().equals(dropDownChoice2.getModelObject()))
                                    .forEach(lectureTime -> {
                                        List<ReportBox> reportBoxList = createToDoPageService.selectReportBox(lectureTime.getId());
                                        reportBoxList.forEach(reportBox -> {
                                            reportBoxDropdown.add(reportBox.getBoxName());
                                            limitDate = reportBox.getLimitTime();
                                            String currentTimestampToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(limitDate);
                                            limitHour = currentTimestampToString.substring(11,13);
                                            limitMinute = currentTimestampToString.substring(14,16);
                                            todoType = "レポートボックス";
                                            reportBoxName = reportBox.getBoxName();
                                            reportBoxContent = reportBox.getContent();
                                        });
                            });
                        });

                //dropDownChoice3.setChoices(reportBoxDropdown)
                target.add(dropDownChoice3,date);
            }
        });
        createToDoForm.add(dropDownChoice1);
        createToDoForm.add(dropDownChoice2);
        createToDoForm.add(dropDownChoice3);

        /*var wmc = new WebMarkupContainer("WMC");
        wmc.setOutputMarkupId(true);
        createToDoForm.add(wmc);
        createToDoForm.add(wmc);

        List<String> limitHourList = new ArrayList<>();
        for(int i=0;i<24;i++)limitHourList.add(Integer.toString(i));
        List<String> limitMinuteList = new ArrayList<>();
        for(int i=0;i<60;i+=5)limitMinuteList.add(Integer.toString(i));
        var limitHourModel = new Model<>(limitHour);
        var limitMinuteModel = new Model<>(limitMinute);
        var limitHourDropDown = new DropDownChoice<>("limitHour",limitHourModel,limitHourList);
        */

        createToDoForm.add(limitHourDropDown);
        createToDoForm.add(limitMinuteDropDown);
        createToDoForm.add(todoTypeDropDown);
        createToDoForm.add(todoNameText);
        createToDoForm.add(todoContentText);

        var updateButton = new AjaxButton("update"){
            @Override
            public void onSubmit(AjaxRequestTarget target){
                modelChanging();
                dateModel.setObject(limitDate);
                limitHourModel.setObject(limitHour);
                limitMinuteModel.setObject(limitMinute);
                textModel.setObject(reportBoxName);
                contentModel.setObject(reportBoxContent);
                todoTypeModel.setObject("レポートボックス");
                modelChanged();

                //target.add(date,todoContentText,todoNameText,todoTypeDropDown,wmc,limitMinuteDropDown);
                //todoContentText,todoNameText,todoTypeDropDown,wmc,limitMinuteDropDown
                target.appendJavaScript("alert('date.getModelObject()');");

                System.out.println("-----------");
                System.out.println(todoContentText.getModelObject());
                System.out.println("----------");

                //setResponsePage(new CreateToDoPage(date.getModelObject(),limitHourDropDown.getModelObject(),limitMinuteDropDown.getModelObject(),(String) todoNameText.getModelObject(),todoContentText.getModelObject()));
                setResponsePage(new CreateToDoPage(limitDate,limitHour,limitMinute,todoType,reportBoxName,reportBoxContent));
            }
        };
        createToDoForm.add(updateButton);


        //Date型→String型の変換処理
        System.out.println("変換前だよ；" + limitDate);
        String currentTimestampToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(limitDate);
        System.out.println("変換したよ：" + currentTimestampToString + "--終わり");
        String dateA = currentTimestampToString.substring(0,10);
        System.out.println("日付：" + currentTimestampToString.substring(0,10));
        String timeB = currentTimestampToString.substring(11,13);
        System.out.println("時間：" + currentTimestampToString.substring(11,13));
        String minitsC = currentTimestampToString.substring(14,16);
        System.out.println("分：" + currentTimestampToString.substring(14,16));
        System.out.println("--------------------------------------------------");

        //String型→Date型の変換処理
        System.out.println("変換前だよ；" + currentTimestampToString);
        String test = dateA + " " + timeB + ":" + minitsC + ":00";
        System.out.println("まって：" + test);
        //parseでエラーがでるためtry-catchが必要
        try {
            SimpleDateFormat beforeDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date a = beforeDate.parse(test);
            System.out.println("変換後だよ");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        var b = new Button("b"){
            @Override
            public void onSubmit(){

                System.out.println(date.getModelObject());
                System.out.println(todoNameText.getModelObject());
                System.out.println("b");
                System.out.println(todoContentText.getModelObject());
                System.out.println(todoTypeDropDown.getModelObject());
                String limitDateToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date.getModelObject()).substring(0,10);
                System.out.println(limitDateToString);
                String limit = limitDateToString + " " + limitHourDropDown.getModelObject() + ":" + limitMinuteDropDown.getModelObject() + ":00";
                try {
                    Timestamp ts = new Timestamp(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(limit).getTime());
                    System.out.println(ts);
                    createToDoPageService.insertToDo(todoNameText.getModelObject().toString(),todoContentText.getModelObject(),ts,"b2182290",todoTypeDropDown.getModelObject().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        };
        createToDoForm.add(b);



        var submitToDoForm = new Form<>("submitToDoForm");
        add(submitToDoForm);
        var submitToDoButton = new Button("submitToDo"){
            @Override
            public void onSubmit(){
                /*
                System.out.println("a");
                System.out.println(todoNameText.getModelObject());
                System.out.println("b");
                System.out.println(todoContentText.getModelObject());
                System.out.println(todoTypeDropDown.getModelObject());
                String limitDateToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(limitDate).substring(0,10);
                System.out.println(limitDateToString);
                String limit = limitDateToString + " " + limitHourDropDown.getModelObject() + ":" + limitMinuteDropDown.getModelObject() + ":00";
                try {
                    Timestamp ts = new Timestamp(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(limit).getTime());
                    System.out.println(ts);
                    createToDoPageService.insertToDo(todoNameText.getModelObject(),todoContentText.getModelObject(),ts,"b2182290",todoTypeDropDown.getModelObject());
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/
            }
        };
        submitToDoForm.add(submitToDoButton);

    }
}
