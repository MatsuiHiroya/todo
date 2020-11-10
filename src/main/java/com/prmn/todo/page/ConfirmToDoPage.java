package com.prmn.todo.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

//ToDo確認
@MountPath("ConfirmToDo")
public class ConfirmToDoPage extends WebPage {

    public ConfirmToDoPage() {
        //ヘッダーのボタンからTopPageへの移動
        var toTopLink = new BookmarkablePageLink<>("toTopPage", TopPage.class);
        add(toTopLink);
        //ヘッダーのボタンからToDoPageへの移動
        var toToDoLink = new BookmarkablePageLink<>("toToDoPage", ToDoPage.class);
        add(toToDoLink);
        //左側のスケジュール部分からToDo作成ページへの移動
        var toCreateToDoLink = new BookmarkablePageLink<>("toCreateToDoPage", CreateToDoPage.class);
        add(toCreateToDoLink);
        //左側のスケジュール部分からToDo設定ページへの移動
        var toConfigurationToDoLink = new BookmarkablePageLink<>("toConfigurationToDoPage", ConfigurationToDoPage.class);
        add(toConfigurationToDoLink);
    }

}
