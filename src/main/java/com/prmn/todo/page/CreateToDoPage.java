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
    private Date limitDate = new Date();
    //private IModel dateModel = Model.of(new Date());
    private String reportBoxName;
    private String reportBoxContent;
    //private ReportBox selectedReportBox;

    @SpringBean
    private ICreateToDoPageService createToDoPageService;

    public CreateToDoPage() {
        var toTopLink = new BookmarkablePageLink<>("toTopPage",TopPage.class);
        add(toTopLink);
        var toToDoListLink = new BookmarkablePageLink<>("toToDoListPage",ToDoListPage.class);
        add(toToDoListLink);
        var toConfigurationToDoLink = new BookmarkablePageLink<>("toConfigurationToDoPage",ConfigurationToDoPage.class);
        add(toConfigurationToDoLink);

        var createToDoForm = new Form<>("createToDoForm");
        add(createToDoForm);

        List<String> lectureNameDropdown = new ArrayList<>(){};
        List<LectureInfo> lectureInfoList= createToDoPageService.selectLectureInfo("b2182290");
        lectureInfoList.forEach(lectureInfo -> {
            lectureNameDropdown.add(lectureInfo.getLectureName());
        });

        List<String> lectureTimeDropdown = new ArrayList<>(){};
        lectureTimeDropdown.add("講義名を選択");
        List<String> reportBoxDropdown = new ArrayList<>(){};
        reportBoxDropdown.add("講義名を選択");

        DropDownChoice dropDownChoice1 = new DropDownChoice<>("lectureNameDropdown", new Model<>(),lectureNameDropdown);
        System.out.println(dropDownChoice1);
        DropDownChoice dropDownChoice2 = new DropDownChoice<>("lectureTimeDropdown", new Model<>("講義名を選択"),lectureTimeDropdown);
        DropDownChoice dropDownChoice3 = new DropDownChoice<>("reportBoxDropdown", new Model<>("講義名を選択"),reportBoxDropdown);
        dropDownChoice2.setOutputMarkupId(true);
        dropDownChoice3.setOutputMarkupId(true);

        dropDownChoice1.add(new OnChangeAjaxBehavior(){
            protected void onUpdate(AjaxRequestTarget target) {

                lectureTimeDropdown.clear();
                //reportBoxDropdown.clear();

                lectureInfoList.stream()
                        .filter(lectureInfo -> lectureInfo.getLectureName().equals(dropDownChoice1.getModelObject()))
                        .forEach(lectureInfo -> {
                            List<LectureTime> lectureTimeList = createToDoPageService.selectLectureTime(lectureInfo.getId());
                            lectureTimeList.stream().forEach(lectureTime -> {
                                lectureTimeDropdown.add(lectureTime.getTimes());
                            });
                        });

                //dropDownChoice2.setChoices(lectureTimeDropdown);
                //dropDownChoice3.setChoices(reportBoxDropdown);
                //target.add(dropDownChoice2,dropDownChoice3);
                target.add(dropDownChoice2);
            }
        });

        IModel dateModel = Model.of(new Date());
        DateTextField date = new DateTextField("date", dateModel);
        this.add(new FeedbackPanel("feedBack"));
        createToDoForm.add(date);
        date.add(new DatePicker());
        date.setOutputMarkupId(true);


        IModel todoTypeModel = Model.of("");
        dropDownChoice2.add(new OnChangeAjaxBehavior(){
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
                                            //selectedReportBox = reportBox;
                                            //selectedReportBox.setBoxName(reportBox.getBoxName());
                                            //selectedReportBox.setContent(reportBox.getContent());
                                            //selectedReportBox.setLimitTime(reportBox.getLimitTime());
                                            //dateModel = Model.of(reportBox.getLimitTime());
                                            limitDate = reportBox.getLimitTime();
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

        List<String> todoType = Arrays.asList("講義","レポートボックス","その他");
        var todoTypeDropDown = new DropDownChoice<>("todoTypeDropdown",todoTypeModel ,todoType);
        createToDoForm.add(todoTypeDropDown);
        todoTypeDropDown.setOutputMarkupId(true);

        var textModel = Model.of("");
        var todoNameText = new TextField("todoNameTextBox", textModel);
        createToDoForm.add(todoNameText);
        todoNameText.setOutputMarkupId(true);

        var contentModel = Model.of("");
        var todoContentText = new TextArea<>("todoContentTextBox",contentModel);
        createToDoForm.add(todoContentText);
        todoContentText.setOutputMarkupId(true);

        AjaxButton updateButton = new AjaxButton("update"){
            @Override
            public void onSubmit(AjaxRequestTarget target){
                super.onSubmit(target);
                dateModel.setObject(limitDate);
                textModel.setObject(reportBoxName);
                contentModel.setObject(reportBoxContent);
                todoTypeModel.setObject("レポートボックス");

                target.add(date,todoContentText,todoNameText,todoTypeDropDown);
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


    }
}
