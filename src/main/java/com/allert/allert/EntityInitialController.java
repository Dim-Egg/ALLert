package com.allert.allert;

import com.allert.allert.classes.Call;
import com.allert.allert.classes.Entity;
import com.allert.allert.classes.Respond;
import com.allert.allert.classes.State;
import com.allert.allert.graphs.contentPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
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

    public void initialize(){
        this.detEmail.setText(currentUser.getEmail());
        this.detName.setText(currentUser.getName());
        this.detPlace.setText(currentUser.getPlace());
        this.detDescription.setText(currentUser.getDescription());
        this.detTelephone.setText(currentUser.getTelephone());

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

                }
            });
            leftTab.getChildren().add(newItem);
        });

    }
}
