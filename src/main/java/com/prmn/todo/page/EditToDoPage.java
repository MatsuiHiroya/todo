package com.prmn.todo.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("EditToDo")
public class EditToDoPage extends WebPage {
    public EditToDoPage() {
        var toTopLink = new BookmarkablePageLink<>("toTopPage",TopPage.class);
        add(toTopLink);
        var toToDoLink = new BookmarkablePageLink<>("toToDoPage",ToDoPage.class);
        add(toToDoLink);
        var toCreateToDoLink = new BookmarkablePageLink<>("toCreateToDoPage",CreateToDoPage.class);
        add(toCreateToDoLink);
        var toConfigurationToDoLink = new BookmarkablePageLink<>("toConfigurationToDoPage",ConfigurationToDoPage.class);
        add(toConfigurationToDoLink);
    }
}
