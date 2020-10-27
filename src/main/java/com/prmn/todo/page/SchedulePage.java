package com.prmn.todo.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("Schedule")
public class SchedulePage extends WebPage {

    public SchedulePage() {
        var toToDoLink = new BookmarkablePageLink<>("toToDoPage", ToDoPage.class);
        add(toToDoLink);
    }

}
