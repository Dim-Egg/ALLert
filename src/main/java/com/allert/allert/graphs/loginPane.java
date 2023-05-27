package com.allert.allert.graphs;

import com.allert.allert.MainApplication;
import com.allert.allert.classes.Call;
import com.allert.allert.classes.Entity;
import com.allert.allert.classes.Respond;
import com.allert.allert.classes.Volunteer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class loginPane extends Pane {
    @FXML
    Label name;
    @FXML
    Label displayProperty;
    @FXML
    Label displayAmount;
    @FXML
    Button logInButton;

    public loginPane(Entity entity){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/loginPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
            AtomicInteger displayCalls = new AtomicInteger(0);
            Call.callsList.forEach(call->{
                if (call.getEntity().equals(entity))
                    displayCalls.getAndIncrement();
            });
            this.displayAmount.setText(displayCalls.toString());
            this.displayProperty.setText("Calls:");
            this.name.setText(entity.getName());
            this.logInButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        MainApplication.logIn(entity.getId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public loginPane(Volunteer volunteer){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/loginPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
            AtomicInteger displayCalls = new AtomicInteger(0);
            Respond.respondList.forEach(respond->{
                if (respond.getVolunteer().equals(volunteer))
                    displayCalls.getAndIncrement();
            });
            this.displayAmount.setText(displayCalls.toString());
            this.displayProperty.setText("Responds:");
            this.name.setText(volunteer.getName());
            this.logInButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        MainApplication.logIn(volunteer.getId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
