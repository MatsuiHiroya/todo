package com.prmn.todo.page;

import com.prmn.todo.bean.ToDo;
import com.prmn.todo.service.IToDoListPageService;
import com.prmn.todo.service.IToDoPageService;
import com.prmn.todo.service.ToDoListPageService;
import javafx.scene.control.RadioButton;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MountPath("ToDoList")
public class ToDoListPage extends WebPage {

    @SpringBean
    private IToDoListPageService toDoListPageService;

    public ToDoListPage() {
        var toTopLink = new BookmarkablePageLink<>("toTopPage", TopPage.class);
        add(toTopLink);
        var toCreateToDoLink = new BookmarkablePageLink<>("toCreateToDoPage", CreateToDoPage.class);
        add(toCreateToDoLink);
        var toConfigurationToDoLink = new BookmarkablePageLink<>("toConfigurationToDoPage", ConfigurationToDoPage.class);
        add(toConfigurationToDoLink);
        var toEditToDoLink = new BookmarkablePageLink<>("toEditToDoPage", EditToDoPage.class);
        add(toEditToDoLink);

        var toCreateToDoForm = new Form<>("toCreateToDoForm");
        add(toCreateToDoForm);
        toCreateToDoForm.add(new Button("toCreateToDoButton"){
            @Override
            public void onSubmit(){
                setResponsePage(new CreateToDoPage());
            }
        });

        WebMarkupContainer todoListWMC = new WebMarkupContainer("todoListWMC");
        todoListWMC.setOutputMarkupId(true);
        add(todoListWMC);

        var deleteBulkCheckBox = new AjaxCheckBox("deleteBulkCheckBox",Model.of(Boolean.TRUE)){
            @Override
            public void onUpdate(AjaxRequestTarget target){
                target.add(todoListWMC);
            }
        };

        //var deleteBulkCheckBox = new CheckBox("deleteBulkCheckBox");

        var cssModel = Model.of("background-color :#98fb98;");

        // Service からデータベースのユーザ一覧をもらい、Modelにする
        // List型のモデルは Model.ofList(...) で作成する。
        var todoListModel = Model.ofList(toDoListPageService.selectToDoList());


        //List型のモデルを表示する　ListView
        var todoLV = new ListView<>("todoList",todoListModel){
            @Override
            protected void populateItem(ListItem<ToDo> listItem) {

                // List型のモデルから、 <li>...</li> ひとつ分に分けられたモデルを取り出す
                var itemModel = listItem.getModel();
                ToDo toDo = itemModel.getObject(); //元々のListの n 番目の要素

                // インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとにとりだして表示する
                // add する先が listItem になることに注意。
                var todoNameModels = Model.of(toDo.getTodoName());
                var todoNameLabels = new Label("todoNames",todoNameModels);
                listItem.add(todoNameLabels);

                var limitTimeModel = Model.of(toDo.getLimitTime());
                var limitTimeLabel = new Label("limitTimes",limitTimeModel);
                listItem.add(limitTimeLabel);

                var todoContentModels = Model.of(toDo.getTodoContent());
                var todoContentLabels = new Label("todoContents",todoContentModels);
                listItem.add(todoContentLabels);

                var editToDoForm = new Form<>("editToDoForm");
                listItem.add(editToDoForm);
                editToDoForm.add(new Button("toEditToDoButton"){
                    @Override
                    public void onSubmit(){
                        setResponsePage(new EditToDoPage());
                    }
                });

                /*PopupSettings settings = new PopupSettings();
                settings.setWindowName("_blank");

                queue(new ExternalLink("deleteToDoButton", "http://google.com").setPopupSettings(settings));*/

                var deleteToDoName = new Label("deleteToDoName",todoNameModels);
                listItem.add(deleteToDoName);

                var deleteToDoForm = new Form<>("deleteToDoForm");
                listItem.add(deleteToDoForm);



                deleteBulkCheckBox.setOutputMarkupPlaceholderTag(true);
                //deleteBulkCheckBox.setVisible(false);

                AjaxButton ajaxButton = new AjaxButton("deleteToDoButton"){
                    @Override
                    public void onSubmit(AjaxRequestTarget target){
                        super.onSubmit(target);
                        toDoListPageService.deleteToDoList(toDo.getId());
                        //実際には、selectToDoListの引数にセッションからのaccount_idを渡す
                        cssModel.setObject("background-color :#98fb98;");
                        todoListModel.setObject(toDoListPageService.selectToDoList());
                        target.add(todoListWMC);
                    }
                };
                deleteToDoForm.add(ajaxButton);

                String s = "1fgg";
                char ss = s.charAt(0);
                int sss = ss;

                listItem.add(deleteBulkCheckBox);

                //if(deleteBulkCheckBox.getConvertedInput() == false){
                    listItem.add(AttributeModifier.replace("class", "todoList2"));
                //}

            }

        };
        todoLV.setOutputMarkupId(true);
        todoListWMC.add(todoLV);

        TransparentWebMarkupContainer bodyContainer = new TransparentWebMarkupContainer("body");
        bodyContainer.add(AttributeModifier.replace("css", cssModel));

        var deleteBulkFormWMC = new WebMarkupContainer("deleteBulkFormWMC");
        deleteBulkFormWMC.setOutputMarkupId(true);
        add(deleteBulkFormWMC);

        var deleteToDoBulkForm = new Form<>("deleteToDoBulkForm");
        deleteBulkFormWMC.add(deleteToDoBulkForm);

        var cancelButton = new AjaxButton("cancelButton"){
            @Override
            public void onSubmit(AjaxRequestTarget target){
                this.setVisible(false);
                deleteBulkCheckBox.setVisible(false);
                deleteBulkCheckBox.setVisible(false);

                target.add(todoListWMC,deleteBulkFormWMC);
            }
        };
        deleteToDoBulkForm.add(cancelButton);
        cancelButton.setOutputMarkupPlaceholderTag(true);
        cancelButton.setVisible(false);

        var deleteToDoBulkButton = new AjaxButton("deleteToDoBulkButton"){
            @Override
            public void onSubmit(AjaxRequestTarget target){
                super.onSubmit();
                cancelButton.setVisible(true);
                deleteBulkCheckBox.setVisible(true);
                target.add(todoListWMC,deleteBulkFormWMC);
            }
        };
        deleteToDoBulkForm.add(deleteToDoBulkButton);



/**
        var editToDoForm = new Form<>("editToDoForm");
        add(editToDoForm);
        editToDoForm.add(new Button("toEditToDoButton"){
            @Override
            public void onSubmit(){
                setResponsePage(new EditToDoPage());
            }
        });

        editToDoForm.add(new Button("deleteToDoButton"){
            @Override
            public void onSubmit(){
                //削除の処理頑張ってね！！！！！！！！
            }
        });*/


    }


}
