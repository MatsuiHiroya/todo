package com.prmn.todo.page;

import com.prmn.todo.bean.ToDo;
import com.prmn.todo.service.IConfirmToDoPageService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import javax.swing.*;


//ToDo確認
@MountPath("ConfirmToDo")
public class ConfirmToDoPage extends WebPage {

    @SpringBean
    private IConfirmToDoPageService confirmToDoPageService;

    public ConfirmToDoPage() {
        //ヘッダーのボタンからTopPageへの移動
        var toTopLink = new BookmarkablePageLink<>("toTopPage", TopPage.class);
        add(toTopLink);
        //ヘッダーのボタンからToDoPageへの移動
        var toToDoListLink = new BookmarkablePageLink<>("toToDoListPage", ToDoListPage.class);
        add(toToDoListLink);
        //左側のスケジュール部分からToDo作成ページへの移動
        var toCreateToDoLink = new BookmarkablePageLink<>("toCreateToDoPage", CreateToDoPage.class);
        add(toCreateToDoLink);
        //左側のスケジュール部分からToDo設定ページへの移動
        var toConfigurationToDoLink = new BookmarkablePageLink<>("toConfigurationToDoPage", ConfigurationToDoPage.class);
        add(toConfigurationToDoLink);


        //ToDoの詳細をDBから持ってくる
        var confirmToDoModel = Model.ofList(confirmToDoPageService.getConfirmToDo(373, "b2181740"));

        //ToDoの詳細を表示する
        var toDoConfirm = new ListView<>("confirmToDoView", confirmToDoModel) {
            @Override
            protected void populateItem(ListItem<ToDo> listItem) {
                var itemModel = listItem.getModel();
                ToDo toDo = itemModel.getObject();

                //ToDoの期限を表示する
                var todoLimitTimeModel = Model.of(toDo.getLimitTime());
                var todoLimitTimeLabel = new Label("todoLimitTime", todoLimitTimeModel);
                listItem.add(todoLimitTimeLabel);

                //ToDoの種類を表示する
                var todoTypeModel = Model.of(toDo.getType());
                var todoTypeLabel = new Label("todoType", todoTypeModel);
                listItem.add(todoTypeLabel);

                //ToDoの名前を表示する
                var todoNameModel = Model.of(toDo.getTodoName());
                var todoNameLabel = new Label("todoName", todoNameModel);
                listItem.add(todoNameLabel);

                //toDoの詳細を表示する
                var todoContentModel = Model.of(toDo.getTodoContent());
                var todoContentLabel = new Label("todoContent", todoContentModel);
                listItem.add(todoContentLabel);
            }
        };
        add(toDoConfirm);

        //削除、戻るボタン用のフォーム
        var confirmToDoForm = new Form<>("ConfirmToDoForm");
        add(confirmToDoForm);

        //戻るボタンを押したときの処理。ToDoページへ遷移させる。
        confirmToDoForm.add(new Button("toToDoButton"){
            @Override
            public void onSubmit(){
                setResponsePage(new ToDoListPage());
            }
        });

        //削除ボタンを押したときの処理。
        //表示している詳細画面のToDoを削除し、ToDoページへ移動させる。
        AjaxButton ajaxButton = new AjaxButton("DeleteToDoButton"){
            @Override
            public void onSubmit(AjaxRequestTarget target){
                super.onSubmit(target);
                confirmToDoPageService.DeleteConfirmToDo(373, "b2181740");
                setResponsePage(new ToDoListPage());
            }
        };
        confirmToDoForm.add(ajaxButton);
        //ajaxButton.setOutputMarkupPlaceholderTag(true);
        //ajaxButton.setVisible(false);

    }

}
