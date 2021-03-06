package com.prmn.todo.page;

import com.prmn.todo.bean.ToDo;
import com.prmn.todo.service.IEditToDoPageService;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.jwicket.ui.datepicker.DatePicker;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@MountPath("EditToDo")
public class EditToDoPage extends WebPage {
    @SpringBean
    private IEditToDoPageService editToDoPageService;

    public EditToDoPage(long todoId) {
        var toTopLink = new BookmarkablePageLink<>("toTopPage", TopPage.class);
        add(toTopLink);
        var toToDoListLink = new BookmarkablePageLink<>("toToDoListPage", ToDoListPage.class);
        add(toToDoListLink);
        var toCreateToDoLink = new Link<>("toCreateToDoPage"){
            @Override
            public void onClick(){
                Date limitDate = new Date();
                String limitHour = "0";
                String limitMinute = "0";
                String todoType = "その他";
                String reportBoxName = "";
                String reportBoxContent = "";
                setResponsePage(new CreateToDoPage(limitDate,limitHour,limitMinute,todoType,reportBoxName,reportBoxContent));
            }
        };
        add(toCreateToDoLink);
        var toConfigurationToDoLink = new BookmarkablePageLink<>("toConfigurationToDoPage", ConfigurationToDoPage.class);
        add(toConfigurationToDoLink);

        var editListModel = Model.ofList(editToDoPageService.showEditToDo(todoId));
        ToDo toDo = editListModel.getObject().get(0);

        var editForm = new Form<>("editForm");
        add(editForm);

        DateTextField date = new DateTextField("limitTime", new Model<>(toDo.getLimitTime()));
        this.add(editForm);
        editForm.add(date);
        date.add(new DatePicker());

        String currentTimestampToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(toDo.getLimitTime());
        var limitHour = currentTimestampToString.substring(11,13);
        var limitMinute = currentTimestampToString.substring(14,16);

        List<String> limitHourList = new ArrayList<>();
        for(int i=0;i<24;i++)limitHourList.add(Integer.toString(i));
        var hourDropdown = new DropDownChoice("hour",new Model(limitHour),limitHourList);
        editForm.add(hourDropdown);

        List<String> limitMinuteList = new ArrayList<>();
        for(int i=0;i<60;i+=5)limitMinuteList.add(Integer.toString(i));
        DropDownChoice<String> minuteDropdown = new DropDownChoice("minute",new Model(limitMinute),limitMinuteList);
        editForm.add(minuteDropdown);

        List<String> typeDropdown = new ArrayList<>(){};
        typeDropdown.addAll(Arrays.asList("講義","アンケート","その他"));
        DropDownChoice<String> dropDownChoice = new DropDownChoice("type",new Model(toDo.getType()),typeDropdown);
        editForm.add(dropDownChoice);

        var todoName = new TextField("todoName",new Model<>(toDo.getTodoName()));
        editForm.add(todoName);

        var todoContent = new TextArea("todoContent",new Model<>(toDo.getTodoContent()));
        editForm.add(todoContent);

        editForm.add(new Button("update") {
            @Override
            public void onSubmit() {
                try {
                    String limitDateToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date.getModelObject()).substring(0,10);
                    String limit = limitDateToString + " " + hourDropdown.getModelObject() + ":" + minuteDropdown.getModelObject() + ":00";
                    Timestamp ts = new Timestamp(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(limit).getTime());

                    editToDoPageService.updateToDo(todoName.getModelObject().toString(), todoContent.getModelObject().toString(), ts, dropDownChoice.getModelObject(),todoId);
                    setResponsePage(ToDoListPage.class);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });
        editForm.add(new Button("toToDoListPage") {
            @Override
            public void onSubmit() {
                setResponsePage(ToDoListPage.class);
            }
        });
    }
}