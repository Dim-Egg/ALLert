package com.allert.allert;

import com.allert.allert.classes.*;
import com.allert.allert.graphs.NotificationPane;
import com.allert.allert.graphs.contentPane;
import com.allert.allert.graphs.entityCallController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class EntityInitialController {


    public VBox leftTab;
    public Button aitimaButton;
    public CheckBox completButton;
    public Button newButton;
    public Label detName;
    public Label detPlace;
    public Label detEmail;
    public Label detTelephone;
    public TextArea detDescription;

    public static Entity currentUser;
    public Button logOutButton;

    public static Stage secondaryWindow;

    public EntityInitialController entityInitialController;
    public Button notButton;

    public void initialize(){
        entityInitialController = this;
        this.detEmail.setText(currentUser.getEmail());
        this.detName.setText(currentUser.getName());
        this.detPlace.setText(currentUser.getPlace());
        this.detDescription.setText(currentUser.getDescription());
        this.detTelephone.setText(currentUser.getTelephone());

        notButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            new NotificationPane(currentUser,entityInitialController);


            }
        });
        newButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                entityCallController entityCallController = new entityCallController(null,entityInitialController, null);


            }
        });
        logOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("dummyLogIn.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 600, 400);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage mainWindow = MainApplication.mainWindow;
                mainWindow.setTitle("Login");
                mainWindow.setResizable(false);
                mainWindow.setScene(scene);
                mainWindow.centerOnScreen();
            }
        });

        addCalls();

        aitimaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("aitimaKrisis-view.fxml"));
                Scene scene;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                secondaryWindow = new Stage();
                secondaryWindow.setTitle("Aitima Krisis");

                secondaryWindow.setScene(scene);
                secondaryWindow.show();
                secondaryWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent windowEvent) {
                        MainApplication.mainWindow.show();
                    }
                });
                MainApplication.mainWindow.hide();
            }
        });

    }
    public void addCalls(){
        leftTab.getChildren().clear();
        Call.callsList.stream().filter(call -> call.getEntity().equals(currentUser)).toList().forEach(call -> {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            long days;
            try {
                Date callDate = sdf.parse(call.getDate());

                days = TimeUnit.DAYS.convert(Math.abs(callDate.getTime()-(new Date()).getTime()),TimeUnit.MILLISECONDS);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


            contentPane newItem = new contentPane(call.getDescription(),
                    call.getCrisis().getName(),call.getTitle(),"Call",Integer.toString(call.getId()));
            newItem.status.setVisible(true);

            newItem.status.setText(days +" days ago");
            if(days>1)
                newItem.status.setStyle("-fx-text-fill:red");

            if(!Respond.respondList.stream().filter(respond -> respond.getCall().equals(call)&&respond.getState().equals(State.PENDING)).toList().isEmpty())
                newItem.notification.setVisible(true);
            newItem.contentButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    entityCallController entityCallController = new entityCallController(call,entityInitialController, null);
                }
            });
            leftTab.getChildren().add(newItem);
        });
    }
}
