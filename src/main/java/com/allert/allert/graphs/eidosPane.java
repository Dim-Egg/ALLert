package com.allert.allert.graphs;

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
}
