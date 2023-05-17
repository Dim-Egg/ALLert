package com.allert.allert.graphs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class contentPane extends Pane{
    public contentPane (){
        FXMLLoader loader = getClass().getResource("");
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public Button contentButton;

    @FXML
    public TextArea contentDescription;

    @FXML
    public TextField contentDetails;

    @FXML
    public TextField contentTitle;
}