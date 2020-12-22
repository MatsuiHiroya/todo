package com.prmn.todo.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Date;

@MountPath("ToDo")
public class ToDoPage extends WebPage {

    public ToDoPage() {
        var toTopLink = new BookmarkablePageLink<>("toTopPage",TopPage.class);
        add(toTopLink);
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
        var toConfigurationToDoLink = new BookmarkablePageLink<>("toConfigurationToDoPage",ConfigurationToDoPage.class);
        add(toConfigurationToDoLink);
        var toEditToDoLink = new BookmarkablePageLink<>("toEditToDoPage",EditToDoPage.class);
        add(toEditToDoLink);

        var editToDoForm = new Form<>("editToDoForm");
        add(editToDoForm);
        editToDoForm.add(new Button("toEditToDoButton"){
            @Override
            public void onSubmit(){
                setResponsePage(new EditToDoPage(1));
            }
        });
    }
}
