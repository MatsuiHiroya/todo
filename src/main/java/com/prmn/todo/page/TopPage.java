package com.prmn.todo.page;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.prmn.todo.bean.ToDo;
import com.prmn.todo.service.ITopPageService;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.time.LocalDateTime;
import java.util.List;


@WicketHomePage
@MountPath("Top")
public class TopPage extends WebPage {

    private String accountId = "b2182290";

    @SpringBean
    private ITopPageService topPageService;

    public TopPage() {
//        var toToDoLink = new BookmarkablePageLink<>("toToDoPage", ToDoPage.class);
//        add(toToDoLink);
        var toDoListLink = new BookmarkablePageLink<>("toToDoListPage", ToDoListPage.class);
        add(toDoListLink);


        //通知の設定状況を持ってくる
        var toDoNoticeInfo = topPageService.selectToDoConfig(accountId);
        System.out.println(accountId);

        WebMarkupContainer evenBlock = new WebMarkupContainer("evenBlock");
        add(evenBlock);
        WebMarkupContainer oddBlock = new WebMarkupContainer("oddBlock");
        add(oddBlock);

        //通知設定のON,OFFによって処理を変える
        if (toDoNoticeInfo.get(0).isTodoConfig() == true) {
            System.out.println("true");
            evenBlock.setVisible(true);
            oddBlock.setVisible(false);
        } else {
            System.out.println("だめぇ");
            evenBlock.setVisible(false);
            oddBlock.setVisible(true);
        }

        //ToDo通知の期限の判断のための時刻を取得
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime limitTime = nowDateTime.plusDays(toDoNoticeInfo.get(0).getDueDate());
        System.out.println(nowDateTime);
        System.out.println(limitTime);

        //通知期限内のtodoリストを持ってくる
        var configToDoListModel = Model.ofList(
                topPageService.selectConfigToDoList(accountId, limitTime, nowDateTime));

        //TopPageにToDoの状況を表示する
        var configToDoList = new ListView<>("configToDoList", configToDoListModel) {
            @Override
            protected void populateItem(ListItem<ToDo> listItem) {
                var itemModel = listItem.getModel();
                ToDo todo = itemModel.getObject();

                //ToDoの期限を表示する
                var todoLimitTimeModel = Model.of(todo.getLimitTime());
                var todoLimitTimeLabel = new Label("todoLimitTime", todoLimitTimeModel);
                listItem.add(todoLimitTimeLabel);

                var todoNameModel = Model.of(todo.getTodoName());
                var todoNameLabel = new Label("todoName", todoNameModel);
                listItem.add(todoNameLabel);
            }
        };
        evenBlock.add(configToDoList);

        //OFFのときの処理
        var todoNoticeModel = Model.of("ToDoの通知設定をOFFにしています。");
        var todoNoticeLabel = new Label("offMessage", todoNoticeModel);
        oddBlock.add(todoNoticeLabel);

    }

}
