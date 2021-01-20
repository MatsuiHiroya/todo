package com.prmn.todo.page;
import com.prmn.todo.bean.ToDo;
import com.prmn.todo.service.IToDoListPageService;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import java.util.*;

@MountPath("ToDoList")
public class ToDoListPage extends WebPage {
    private AjaxCheckBox deleteBulkCheckBox;
    private AjaxButton checkButton;
    private AjaxButton deleteToDoBulkButton;
    private boolean isView = false;
    protected Map<Long, ToDo> checkedMap = new HashMap<>();
    //private Boolean isChecked = false;

    @SpringBean
    private IToDoListPageService toDoListPageService;
    public ToDoListPage() {
        var toTopLink = new BookmarkablePageLink<>("toTopPage", TopPage.class);
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
        var toConfigurationToDoLink = new BookmarkablePageLink<>("toConfigurationToDoPage", ConfigurationToDoPage.class);
        add(toConfigurationToDoLink);
        var toCreateToDoForm = new Form<>("toCreateToDoForm");
        add(toCreateToDoForm);
        toCreateToDoForm.add(new Button("toCreateToDoButton"){
            @Override
            public void onSubmit(){
                Date limitDate = new Date();
                String limitHour = "0";
                String limitMinute = "0";
                String todoType = "その他";
                String reportBoxName = "";
                String reportBoxContent = "";
                setResponsePage(new CreateToDoPage(limitDate,limitHour,limitMinute,todoType,reportBoxName,reportBoxContent));
            }
        });

        WebMarkupContainer todoListWMC = new WebMarkupContainer("todoListWMC");
        todoListWMC.setOutputMarkupId(true);
        add(todoListWMC);

        var todoListModel = Model.ofList(toDoListPageService.selectToDoList());
        var todoLV = new ListView<>("todoList",todoListModel){
            @Override
            protected void populateItem(ListItem<ToDo> listItem) {

                var itemModel = listItem.getModel();
                ToDo toDo = itemModel.getObject();

                var todoNameModels = Model.of(toDo.getTodoName());
                var toConfirmToDoPageLink = new Link<>("toConfirmToDoPage"){
                    @Override
                    public void onClick(){
                        setResponsePage(new ConfirmToDoPage(toDo.getId(),toDo.getAccountId()));
                    }
                };
                var todoNameLabels = new Label("todoNames",todoNameModels);
                toConfirmToDoPageLink.add(todoNameLabels);
                listItem.add(toConfirmToDoPageLink);

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
                        setResponsePage(new EditToDoPage(toDo.getId()));
                    }
                });

                var deleteToDoName = new Label("deleteToDoName",todoNameModels);
                listItem.add(deleteToDoName);
                var deleteToDoForm = new Form<>("deleteToDoForm");
                listItem.add(deleteToDoForm);
                //listItem.add(new Check("deleteBulkCheckBox", listItem.getModel()));
                //deleteBulkCheckBox = new AjaxCheckBox("deleteBulkCheckBox",Model.of(Boolean.FALSE)){
                deleteBulkCheckBox = new AjaxCheckBox("deleteBulkCheckBox",new Model<>(toDo.getChecked())){
                    @Override
                    public void onUpdate(AjaxRequestTarget target){
                        toDo.setChecked(this.getModelObject());
                        if (getModelObject()) checkedMap.put(toDo.getId(), toDo);
                        else checkedMap.remove(toDo.getId());
                        target.add(todoListWMC);
                    }
                };
                deleteBulkCheckBox.setOutputMarkupPlaceholderTag(true);
                deleteBulkCheckBox.setVisible(isView);
                AjaxButton ajaxButton = new AjaxButton("deleteToDoButton"){
                    @Override
                    public void onSubmit(AjaxRequestTarget target){
                        super.onSubmit(target);
                        toDoListPageService.deleteToDoList(toDo.getId());
                        //実際には、selectToDoListの引数にセッションからのaccount_idを渡す
                        todoListModel.setObject(toDoListPageService.selectToDoList());
                        target.add(todoListWMC);
                    }
                };
                deleteToDoForm.add(ajaxButton);
                listItem.add(deleteBulkCheckBox);


                var cssModel = new IModel<String>() {
                    @Override
                    public String getObject() {
                        return listItem.getModelObject().getChecked() ? "background-color: #f0ffeb" : "background-color: #ffffff";
                    }
                };
                //cssModel.setObject("background-color: #a9a9a9");

                listItem.add(new AttributeModifier("style",cssModel));

            }
        };
        todoLV.setOutputMarkupId(true);
        todoListWMC.add(todoLV);
        var deleteBulkFormWMC = new WebMarkupContainer("deleteBulkFormWMC");
        deleteBulkFormWMC.setOutputMarkupId(true);
        add(deleteBulkFormWMC);
        var deleteToDoBulkForm = new Form<>("deleteToDoBulkForm");
        deleteBulkFormWMC.add(deleteToDoBulkForm);


        var allCheckButton = new AjaxButton("allCheck"){
            @Override
            public void onSubmit(AjaxRequestTarget target){
                todoListModel.getObject().forEach(todolist ->{
                            todolist.setChecked(true);
                            checkedMap.put(todolist.getId(), todolist);
                        });
                target.add(todoListWMC);
            }
        };
        deleteToDoBulkForm.add(allCheckButton);
        allCheckButton.setOutputMarkupId(true);
        allCheckButton.setVisible(false);
        var allReleaseButton = new AjaxButton("allRelease"){
            @Override
            public void onSubmit(AjaxRequestTarget target){
                todoListModel.getObject().forEach(todolist -> {
                    todolist.setChecked(false);
                    checkedMap.clear();
                });
                target.add(todoListWMC);
            }
        };
        deleteToDoBulkForm.add(allReleaseButton);
        allReleaseButton.setOutputMarkupId(true);
        allReleaseButton.setVisible(false);

        var cancelButton = new AjaxButton("cancelButton"){
            @Override
            public void onSubmit(AjaxRequestTarget target){
                this.setVisible(false);
                deleteToDoBulkButton.setVisible(false);
                checkButton.setVisible(true);
                allCheckButton.setVisible(false);
                allReleaseButton.setVisible(false);
                isView = false;

                //deleteBulkCheckBox.setVisible(false);
                target.add(todoListWMC,deleteBulkFormWMC);
            }
        };
        deleteToDoBulkForm.add(cancelButton);
        cancelButton.setOutputMarkupPlaceholderTag(true);
        cancelButton.setVisible(false);


        deleteToDoBulkButton = new AjaxButton("deleteToDoBulkButton"){
            @Override
            public void onSubmit(AjaxRequestTarget target){
                super.onSubmit();
                cancelButton.setVisible(false);
                allCheckButton.setVisible(false);
                allReleaseButton.setVisible(false);
                this.setVisible(false);
                checkButton.setVisible(true);
                isView = false;

                for (Map.Entry<Long, ToDo> entry : checkedMap.entrySet()) {
                    toDoListPageService.deleteToDoList(entry.getKey());
                }
                todoListModel.setObject(toDoListPageService.selectToDoList());

                //deleteBulkCheckBox.setVisible(true);
                target.add(todoListWMC,deleteBulkFormWMC);
            }
        };
        deleteToDoBulkForm.add(deleteToDoBulkButton);
        deleteToDoBulkButton.setOutputMarkupPlaceholderTag(true);
        deleteToDoBulkButton.setVisible(false);

        checkButton = new AjaxButton("checkButton"){
            @Override
            public void onSubmit(AjaxRequestTarget target){
                super.onSubmit();
                cancelButton.setVisible(true);
                deleteToDoBulkButton.setVisible(true);
                allCheckButton.setVisible(true);
                allReleaseButton.setVisible(true);
                this.setVisible(false);
                //deleteBulkCheckBox.setVisible(true);
                isView = true;
                target.add(todoListWMC,deleteBulkFormWMC);
            }
        };
        deleteToDoBulkForm.add(checkButton);
        checkButton.setOutputMarkupPlaceholderTag(true);
    }
}