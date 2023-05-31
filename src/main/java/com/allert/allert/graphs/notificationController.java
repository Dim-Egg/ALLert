package com.allert.allert.graphs;

import com.allert.allert.EntityInitialController;
import com.allert.allert.classes.Need_Request;
import com.allert.allert.classes.Notification;
import com.allert.allert.classes.Volunteer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class notificationController extends Pane {
    public Label crisisName;
    public Button moreButton;

    public notificationController (Need_Request request, Notification notification, EntityInitialController entityInitialController, NotificationPane notificationPane){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/needReqNotification.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();


        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        crisisName.setText(request.getCrisis().getName());

        moreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Notification.delete(notification);
                entityCallController entityCallController = new entityCallController(null,entityInitialController, (Volunteer)request.getUser());
                entityCallController.criChoise.getSelectionModel().select(request.getCrisis().getName());
                entityCallController.innerCallDescription.setText(request.getDescription());
                notificationPane.close();
            }
        });
    }
}
