package com.allert.allert.graphs;

import com.allert.allert.classes.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VolSelection extends AnchorPane {
    public VBox titledContainer;
    public Button saveButton;
    public Button cancelButton;
    public Stage editWindow;

    public VolSelection(Call call){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/volSelection.fxml"));
        loader.setRoot(this);
        loader.setController(this);


        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        editWindow = new Stage();
        editWindow.setTitle("Edit Volunteers");
        editWindow.setResizable(false);

        editWindow.setScene(scene);
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });
        List<VolItem> titlesList = new ArrayList<>();
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                titlesList.forEach(volItem -> {
                    volItem.volunteerSelectionPaneList.forEach(volunteerSelectionPane -> {
                        if(volunteerSelectionPane.acceptButton.isDefaultButton()){

                        }
                    });
                });
            }
        });


        for (Item item : call.getHelp_List()[1].getItem_list()) {
            VolItem item1 = new VolItem((Volunteer_Item) item);
            titledContainer.getChildren().add(item1);
            titlesList.add(item1);

        }

        Respond.respondList.stream().filter(respond -> respond.getCall().equals(call)&&!respond.getHelp_list()[1].isEmpty()).toList().forEach(respond -> {

            for (Item item : respond.getHelp_list()[1].getItem_list()) {
                VolItem titleItem = titlesList.stream().filter(volItem -> volItem.getText().contains(item.getName())).toList().get(0);
                if(respond.getState().equals(State.PENDING)){
                    VolunteerSelectionPane pane = new VolunteerSelectionPane();
                    pane.name.setText(respond.getVolunteer().getName());
                    titleItem.add(pane);
                    pane.acceptButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if(!pane.acceptButton.isDefaultButton()){
                            pane.acceptButton.setDefaultButton(true);
                            pane.declineButton.setDefaultButton(false);
                            titleItem.increase();}
                        }
                    });
                    pane.declineButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if(pane.acceptButton.isDefaultButton()) {
                                pane.acceptButton.setDefaultButton(false);
                                pane.declineButton.setDefaultButton(true);
                                titleItem.decrease();
                            } else if (!pane.acceptButton.isDefaultButton()&&!pane.declineButton.isDefaultButton()) {
                                pane.declineButton.setDefaultButton(true);
                            }
                        }
                    });



                }


            }

        });

    }
    public void show(){
        editWindow.initModality(Modality.APPLICATION_MODAL);
        editWindow.showAndWait();
    }
    public void close(){
        editWindow.close();
    }
}
