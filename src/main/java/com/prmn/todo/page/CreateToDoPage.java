package com.prmn.todo.page;

import javafx.scene.control.DatePicker;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.wicketstuff.annotation.mount.MountPath;

//import org.apache.wicket.extensions.markup.html.form.DateTextField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@MountPath("CreateToDo")
public class CreateToDoPage extends WebPage {
    public CreateToDoPage() {
        var toTopLink = new BookmarkablePageLink<>("toTopPage",TopPage.class);
        add(toTopLink);
        var toToDoLink = new BookmarkablePageLink<>("toToDoPage",ToDoPage.class);
        add(toToDoLink);
        var toConfigurationToDoLink = new BookmarkablePageLink<>("toConfigurationToDoPage",ConfigurationToDoPage.class);
        add(toConfigurationToDoLink);

        var createToDoForm = new Form<>("createToDoForm");
        add(createToDoForm);

        List<String> lectureNameDropdown = new ArrayList<>(){};
        lectureNameDropdown.addAll(Arrays.asList("a","b","c"));
        List<String> lectureTimeDropdown = new ArrayList<>(){};
        lectureTimeDropdown.addAll(Arrays.asList("第一回","第二回","第三回"));
        List<String> reportBoxDropdown = new ArrayList<>(){};
        reportBoxDropdown.addAll(Arrays.asList("レポート1","レポート2","レポート3"));

        createToDoForm.add(new DropDownChoice<>("lectureNameDropdown", new Model<>("b"),lectureNameDropdown));
        createToDoForm.add(new DropDownChoice<>("lectureTimeDropdown", new Model<>("第三回"),lectureTimeDropdown));
        createToDoForm.add(new DropDownChoice<>("reportBoxDropdown", new Model<>("レポート1"),reportBoxDropdown));


        /*<form wicket:id="createTextForm">*/
        //var todoNameText = new TextField<>("todoNameTextBox");
        //var todoNameText = new TextField<LocalDate>("todoNameTextBox",Model.of(LocalDate.now()), LocalDate.class);
        var todoNameText = new DateTextField("todoNameTextBox", new Model<Date>(new Date()));
        createToDoForm.add(todoNameText);

        //new Model<Date>(new Date()), new StyleDateConverter("M-", true)
        var todoContentText = new TextArea<>("todoContentTextBox");
        createToDoForm.add(todoContentText);

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
    }
}
