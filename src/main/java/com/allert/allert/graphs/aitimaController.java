package com.allert.allert.graphs;

import com.allert.allert.MainApplication;
import com.allert.allert.VollunteerInitialContoller;
import com.allert.allert.classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.util.stream.Collectors;

public class aitimaController {
    public Button okButton;
    public Button cancelButton;
    public ChoiceBox criChoises;
    public TextArea aitimaText;

    @FXML
    protected void initialize() {
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean in = false;
                if(criChoises.getSelectionModel().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"You must choose a Crisis");
                    alert.show();
                    in = true;
                }
                if(aitimaText.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Your Description Can't be null");
                    alert.show();
                    in = true;
                }
                if(in)return;
                Need_Request request = new Need_Request(VollunteerInitialContoller.currentUser,aitimaText.getText(),Crisis.findByName(criChoises.getSelectionModel().getSelectedItem().toString()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Saved!");

                VollunteerInitialContoller.secondaryWindow.close();
                MainApplication.mainWindow.show();
                alert.show();
                Call.callsList.stream().filter(call -> call.getCrisis().equals(request.getCrisis())).map(Call::getEntity).distinct().toList().forEach(entity -> {
                    new Notification(entity,request);
                });

            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VollunteerInitialContoller.secondaryWindow.close();
                MainApplication.mainWindow.show();
            }
        });
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(
        Crisis.crisisList.stream().map(Crisis::getName).collect(Collectors.toList()));
        criChoises.setItems(items);
    }

}
