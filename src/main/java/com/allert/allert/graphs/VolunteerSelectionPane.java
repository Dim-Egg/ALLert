package com.allert.allert.graphs;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class VolunteerSelectionPane extends Pane {
    public Hyperlink name;
    public Button acceptButton;
    public Button declineButton;
    public CheckBox confirmCheck;
    public TextArea amountRisen;

    public VolunteerSelectionPane(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/volunteerSelectionPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();


        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
