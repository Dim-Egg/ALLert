package com.allert.allert.graphs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class eidosPane extends Pane {
    public eidosPane (String needAmount, String accAmount, String name){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/eidosPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
            this.accAmount.setText(accAmount);
            this.needAmount.setText(needAmount);
            this.name.setText(name);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    @FXML
    public TextField accAmount;

    @FXML
    public TextField needAmount;

    @FXML
    public Label name;

    @FXML
    public Button yesButton;
    @FXML
    public Button noButton;

    public eidosPane(String name) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/eidosPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
            this.accAmount.setVisible(false);
            this.needAmount.setVisible(false);
            this.yesButton.setVisible(true);
            this.noButton.setVisible(true);

            this.yesButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {yesButton.setDefaultButton(true);noButton.setDefaultButton(false);}});
            this.noButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {noButton.setDefaultButton(true);yesButton.setDefaultButton(false);}});

            this.name.setText(name);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
