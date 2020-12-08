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
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.jwicket.ui.datepicker.DatePicker;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;

//import org.apache.wicket.extensions.markup.html.form.DateTextField;

import java.util.*;


@MountPath("CreateToDo")
public class CreateToDoPage extends WebPage {

    //private List<LectureTime> lectureTimeList;
    //private List<ReportBox> reportBoxList = new ArrayList<>();
    private Date limitDate = new Date();

    @SpringBean
    private ICreateToDoPageService createToDoPageService;

    public CreateToDoPage() {
        var toTopLink = new BookmarkablePageLink<>("toTopPage",TopPage.class);
        add(toTopLink);
        var toToDoLink = new BookmarkablePageLink<>("toToDoPage",ToDoPage.class);
        add(toToDoLink);
        var toConfigurationToDoLink = new BookmarkablePageLink<>("toConfigurationToDoPage",ConfigurationToDoPage.class);
        add(toConfigurationToDoLink);

        var createToDoForm = new Form<>("createToDoForm");
        add(createToDoForm);

        //ドロップダウンの名前とIDを保持するためのマップ
        //Map<String, String> chooseDropDown = new HashMap<>();

        List<String> lectureNameDropdown = new ArrayList<>(){};
        List<LectureInfo> lectureInfoList= createToDoPageService.selectLectureInfo("b2182290");
        lectureInfoList.forEach(lectureInfo -> {
            lectureNameDropdown.add(lectureInfo.getLectureName());
        });

        List<String> lectureTimeDropdown = new ArrayList<>(){};
        lectureTimeDropdown.add("講義名を選択");
        List<String> reportBoxDropdown = new ArrayList<>(){};
        reportBoxDropdown.add("講義名を選択");

        List<LectureTime> lectureTimeList = new ArrayList<>();


        DropDownChoice dropDownChoice1 = new DropDownChoice<>("lectureNameDropdown", new Model<>(),lectureNameDropdown);
        DropDownChoice dropDownChoice2 = new DropDownChoice<>("lectureTimeDropdown", new Model<>("講義名を選択"),lectureTimeDropdown);
        DropDownChoice dropDownChoice3 = new DropDownChoice<>("reportBoxDropdown", new Model<>("講義名を選択"),reportBoxDropdown);
        dropDownChoice2.setOutputMarkupId(true);
        dropDownChoice3.setOutputMarkupId(true);

        dropDownChoice1.add(new OnChangeAjaxBehavior(){
            protected void onUpdate(AjaxRequestTarget target) {

                lectureTimeDropdown.clear();
                lectureTimeDropdown.add("講義名を選択");
                //reportBoxDropdown.clear();

                lectureInfoList.stream()
                        .filter(lectureInfo -> lectureInfo.getLectureName().equals(dropDownChoice1.getModelObject()))
                        .forEach(lectureInfo -> {
                            List<LectureTime> lectureTimeList = createToDoPageService.selectLectureTime(lectureInfo.getId());
                            lectureTimeList.stream().forEach(lectureTime -> {
                                lectureTimeDropdown.add(lectureTime.getTimes());
                            });
                        });

                dropDownChoice2.setChoices(lectureTimeDropdown);
                //dropDownChoice3.setChoices(reportBoxDropdown);
                //target.add(dropDownChoice2,dropDownChoice3);
                target.add(dropDownChoice2);
            }
        });
        dropDownChoice2.add(new OnChangeAjaxBehavior(){
            protected void onUpdate(AjaxRequestTarget target) {
                reportBoxDropdown.clear();
                reportBoxDropdown.add("講義名を選択");

                lectureInfoList.stream()
                        .filter(lectureInfo -> lectureInfo.getLectureName().equals(dropDownChoice1.getModelObject()))
                        .forEach(lectureInfo -> {
                            List<LectureTime> lectureTimeList = createToDoPageService.selectLectureTime(lectureInfo.getId());
                            lectureTimeList.stream()
                                    .filter(lectureTime -> lectureTime.getTimes().equals(dropDownChoice2.getModelObject()))
                                    .forEach(lectureTime -> {
                                        List<ReportBox> reportBoxList = createToDoPageService.selectReportBox(lectureTime.getId());
                                        reportBoxList.forEach(reportBox -> reportBoxDropdown.add(reportBox.getBoxName()));
                            });
                        });

                dropDownChoice3.setChoices(reportBoxDropdown);
                target.add(dropDownChoice3);
            }
        });
        createToDoForm.add(dropDownChoice1);
        createToDoForm.add(dropDownChoice2);
        createToDoForm.add(dropDownChoice3);

        //reportBoxList.stream()
           //     .filter(reportBox -> reportBox.getBoxName() == dropDownChoice3.getModelObject())
             //   .forEach(s -> System.out.println(s.getLimitTime()));
                //.forEach(reportBox -> {limitDate = reportBox.getLimitTime();
                //    System.out.println(limitDate);});

        DateTextField date = new DateTextField("date", new Model<Date>(limitDate));
        this.add(new FeedbackPanel("feedBack"));
        createToDoForm.add(date);
        date.add(new DatePicker());
        date.setOutputMarkupId(true);

        var todoNameText = new TextField("todoNameTextBox", new Model<>());

        createToDoForm.add(todoNameText);

        //new Model<Date>(new Date()), new StyleDateConverter("M-", true)
        var todoContentText = new TextArea<>("todoContentTextBox",new Model<>());
        createToDoForm.add(todoContentText);

        var updateButton = new AjaxButton("update"){
            @Override
            public void onSubmit(AjaxRequestTarget target){
                /**reportBoxList.stream()
                        .filter(reportBox -> reportBox.getBoxName() == dropDownChoice3.getModelObject())
                        .forEach(s -> System.out.println(s.getLimitTime()));
                target.add(date);*/

                lectureInfoList.stream()
                        .filter(lectureInfo -> lectureInfo.getLectureName().equals(dropDownChoice1.getModelObject()))
                        .forEach(lectureInfo -> {
                            System.out.println("bbbbb");
                            List<LectureTime> lectureTimeList = createToDoPageService.selectLectureTime(lectureInfo.getId());
                            lectureTimeList.forEach(lectureTime -> {
                                System.out.println("ccccc");
                                List<ReportBox> reportBoxList = createToDoPageService.selectReportBox(lectureTime.getId());
                                reportBoxList.stream()
                                        //.filter(reportBox -> reportBox.getBoxName().equals(dropDownChoice3.getModelObject()))
                                        .forEach(reportBox -> {
                                            System.out.println(dropDownChoice3.getModelObject());
                                            limitDate = reportBox.getLimitTime();
                                            System.out.println("gwggewra");
                                            System.out.println(limitDate);
                                        });
                            });
                        });
                target.add(date);
            }
        };
        createToDoForm.add(updateButton);

        Button button = new Button("submit"){
            @Override
            public void onSubmit(){
                System.out.println(todoContentText.getModelObject().toString());
            }
        };
        createToDoForm.add(button);


        /**Form<Void> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                super.onSubmit();
            }
        };*/



/**
        DatePicker datePicker = new DatePicker();

        var dateForm = new Form<>("dateForm");
        add(dateForm);

        var dateTextField = new DateTextField("dateTextField",new Model<Date>(new Date()));
        dateForm.add(dateTextField);

        dateForm.add(new Button("dateButton"){
            @Override
            public void onSubmit(){
                //LocalDate date = datePicker.getValue();
                //dateTextField = new DateTextField("dateTextField",new Model<>(date.toString()));
            }
        });*/

        List<String> testDropdown = new ArrayList<>(){};
        testDropdown.addAll(Arrays.asList("a","b","c"));
        var radioForm = new Form<>("radioForm");
        add(radioForm);
        var radioG = new RadioGroup<>("radioG");
        radioForm.add(
                radioG.add(new Radio("1",new Model("pp"))),
                radioG.add(new DropDownChoice<>("testDropDown", new Model<>("b"),testDropdown)),
                radioG.add(new Radio("2",new Model("gg")))
        );
        var radioButton = new Button("radioButton"){
            @Override
            public void onSubmit(){
            }
        };
        radioForm.add(radioButton);
    }
}
