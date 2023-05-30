package com.allert.allert.graphs;

import com.allert.allert.MainApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EditItemController extends AnchorPane {
    public Button saveButton;
    public Button cancelButton;
    public TextArea instructions;
    public Stage editWindow;
    public EditItemController(String title){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/editItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);


        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        editWindow = new Stage();
        editWindow.setTitle(title);
        editWindow.setResizable(false);

        editWindow.setScene(scene);
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });
    }

    public void show(){
        editWindow.initModality(Modality.APPLICATION_MODAL);
        editWindow.showAndWait();
    }
    public void close(){
        editWindow.close();
    }
}
