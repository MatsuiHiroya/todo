package com.prmn.todo.page;

import com.prmn.todo.bean.Notice;
import com.prmn.todo.service.IConfigurationToDoPageService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;


@MountPath("ConfigurationToDo")
public class ConfigurationToDoPage extends WebPage {

    @SpringBean
    private IConfigurationToDoPageService configurationToDoPageService;

    public ConfigurationToDoPage() {
        var toTopLink = new BookmarkablePageLink<>("toTopPage",TopPage.class);
        add(toTopLink);
        var toToDoListLink = new BookmarkablePageLink<>("toToDoListPage",ToDoListPage.class);
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

        List<Notice> toDoConfigList = configurationToDoPageService.selectToDoConfig("b2182330"/**accountId**/);

        List<String> toDoConfigDropdown = new ArrayList<>(){};
        toDoConfigDropdown.addAll(Arrays.asList("1日前","2日前","3日前","4日前","5日前","6日前","7日前"));
        var DropdownChoice = new DropDownChoice<>("toDoConfigDropdown", new Model<>(toDoConfigList.get(0).getDueDate() + "日前"),toDoConfigDropdown);
        var radioForm = new Form<>("radioForm");
        add(radioForm);
        var radioG = new RadioGroup<>("radioG",new Model<>(""));
        radioForm.add(
                radioG.add(new Radio("ToDoConfigOn",new Model("On"))),
                radioG.add(DropdownChoice),
                radioG.add(new Radio("ToDoConfigOff",new Model("Off")))
        );
        radioForm.add(new Button("toConfigurationToDoButton") {
            @Override
            public void onSubmit() {
                boolean toDoConfigFrag = true;

                if (radioG.getModelObject().equals("Off")){
                    toDoConfigFrag = false;
                }

                configurationToDoPageService.updateToDoConfig(toDoConfigFrag, parseInt(DropdownChoice.getModelObject().substring(0,1)), "b2182330"/**accountId**/);

                setResponsePage(ToDoListPage.class);
            }
        });
    }
}
