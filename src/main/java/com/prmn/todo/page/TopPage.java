package com.prmn.todo.page;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

@WicketHomePage
@MountPath("Top")
public class TopPage extends WebPage {

    public TopPage() {
        var toToDoLink = new BookmarkablePageLink<>("toToDoPage", ToDoPage.class);
        add(toToDoLink);
    }

}
