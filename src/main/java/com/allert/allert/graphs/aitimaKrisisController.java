package com.allert.allert.graphs;

import com.allert.allert.EntityInitialController;
import com.allert.allert.MainApplication;
import com.allert.allert.VollunteerInitialContoller;
import com.allert.allert.classes.Crisis;
import com.allert.allert.classes.Crisis_Request;
import com.allert.allert.classes.Need_Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.stream.Collectors;

public class aitimaKrisisController {
    public Button okButton;
    public Button cancelButton;
    public TextArea aitimaText;
    public TextField placeField;
@FXML
    protected void initialize() {
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean in = false;
                if(placeField.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"You must type a place");
                    alert.show();
                    in = true;
                }
                if(aitimaText.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Your Description Can't be null");
                    alert.show();
                    in = true;
                }
                if(in)return;
                new Crisis_Request(EntityInitialController.currentUser,aitimaText.getText(), placeField.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Saved!");

                EntityInitialController.secondaryWindow.close();
                MainApplication.mainWindow.show();
                alert.show();
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EntityInitialController.secondaryWindow.close();
                MainApplication.mainWindow.show();
            }
        });

    }
}
