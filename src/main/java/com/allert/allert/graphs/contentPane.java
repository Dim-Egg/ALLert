package com.allert.allert.graphs;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class contentPane extends Pane{
    public contentPane (String contentDescription, String contentDetails, String contentTitle, String contentType, String contentId){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/contentPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
            this.contentDescription.setText(contentDescription);
            this.contentDetails.setText(contentDetails);
            this.contentTitle.setText(contentTitle);
            this.contentButton.setText("Perissotera");
            this.content.setId(contentType+contentId);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @FXML
    public Pane content;
    @FXML
    public Button contentButton;

    @FXML
    public TextArea contentDescription;

    @FXML
    public TextField contentDetails;

    @FXML
    public TextField contentTitle;

    @FXML
    public Label status;
}