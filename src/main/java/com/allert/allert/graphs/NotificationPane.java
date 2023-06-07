package com.allert.allert.graphs;

import com.allert.allert.EntityInitialController;
import com.allert.allert.VollunteerInitialContoller;
import com.allert.allert.classes.Entity;
import com.allert.allert.classes.Notification;
import com.allert.allert.classes.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class NotificationPane extends AnchorPane {
    @FXML
    public VBox notContent;
    public Stage editWindow;

    public NotificationPane(User user, EntityInitialController entityInitialController){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/notificationPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);


        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        editWindow = new Stage();
        editWindow.setTitle("Notifications");
        editWindow.setResizable(false);

        editWindow.setScene(scene);


        Notification.getNotifications(user).forEach(notification -> {
            if(notification.getNeed_Request() != null){
                notContent.getChildren().add(new notificationController(notification.getNeed_Request(),notification,entityInitialController,this));

            }
            if(notification.getCall()!=null){
                notContent.getChildren().add(new notificationController(notification.getCall(),notification,entityInitialController,this));
            }
        });

        show();
    }

    public NotificationPane(User user, VollunteerInitialContoller vollunteerInitialContoller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/notificationPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);


        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        editWindow = new Stage();
        editWindow.setTitle("Notifications");
        editWindow.setResizable(false);

        editWindow.setScene(scene);

        System.out.println(Notification.getNotifications(user).size());
        Notification.getNotifications(user).forEach(notification -> {
            if(notification.getCall() != null){
                notContent.getChildren().add(new notificationController(notification.getCall(),notification,vollunteerInitialContoller,this));

            } else if (notification.getRespond() != null) {
                notContent.getChildren().add(new notificationController(notification.getRespond(),notification,vollunteerInitialContoller,this));

            }
        });

        show();
    }

    public void show(){
        editWindow.initModality(Modality.APPLICATION_MODAL);
        editWindow.showAndWait();
    }
    public void close(){
        editWindow.close();
    }
}
