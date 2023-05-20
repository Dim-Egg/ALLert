package com.allert.allert;

import com.allert.allert.classes.Call;
import com.allert.allert.graphs.contentPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Iterator;

public class VollunteerInitialContoller {

    public VBox leftTab;
    public TitledPane rightTitle;

    @FXML
    protected void initialize() {
        addAllCalls();


    }

    public void setRightTab(String type,String id){
        if(type.equals("Call") )
            rightTitle.setText(Call.findById(Integer.parseInt(id)).getTitle());
    }
    public void addAllCalls(){
        Call.callsList.forEach((call) -> {

            contentPane newItem = new contentPane(call.getDescription(),
                    call.getCrisis().getName()+" "+call.getDate(),call.getTitle(),"Call",Integer.toString(call.getId()));
            newItem.contentButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setRightTab("Call",Integer.toString(call.getId()));
                }
            });
            leftTab.getChildren().add(newItem);
        });

    }

}

