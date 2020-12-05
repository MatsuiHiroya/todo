package com.prmn.todo.page;

import com.prmn.todo.bean.ToDo;
import com.prmn.todo.service.IEditToDoPageService;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.jwicket.ui.datepicker.DatePicker;

import java.sql.Timestamp;
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
        var toToDoLink = new BookmarkablePageLink<>("toToDoPage", ToDoPage.class);
        add(toToDoLink);
        var toCreateToDoLink = new BookmarkablePageLink<>("toCreateToDoPage", CreateToDoPage.class);
        add(toCreateToDoLink);
        var toConfigurationToDoLink = new BookmarkablePageLink<>("toConfigurationToDoPage", ConfigurationToDoPage.class);
        add(toConfigurationToDoLink);

        var editListModel = Model.ofList(editToDoPageService.showEditToDo(todoId));
        ToDo toDo = editListModel.getObject().get(0);

        var editForm = new Form<>("editForm");
        add(editForm);

        DateTextField date = new DateTextField("limitTime", new Model<Date>(toDo.getLimitTime()));
        //this.add(new FeedbackPanel("feedBack"));
        this.add(editForm);
        editForm.add(date);
        date.add(new DatePicker());

        List<String> typeDropdown = new ArrayList<>(){};
        typeDropdown.addAll(Arrays.asList("講義","アンケート"));
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
                    editToDoPageService.updateToDo(todoName.getModelObject().toString(), todoContent.getModelObject().toString(), new Timestamp(date.getModelObject().getTime()), dropDownChoice.getModelObject(),todoId);
                    setResponsePage(ToDoPage.class);
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