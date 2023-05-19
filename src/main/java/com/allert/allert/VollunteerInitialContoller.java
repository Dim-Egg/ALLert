package com.allert.allert;

import com.allert.allert.graphs.contentPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VollunteerInitialContoller {

    public VBox leftTab;
    public TitledPane rightTitle;

    @FXML
    protected void initialize() {
    contentPane newItem = new contentPane("Stin perioxi tis adravidas xreiazomaste nero",
            "Fotia Adravidas, 17-10-2018","Kalesma gia paroxi nerou","Call","3");
    newItem.contentButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            setRightTab("Call","3");
        }
    });
        leftTab.getChildren().add(newItem);


    }

    public void setRightTab(String type,String id){
        rightTitle.setText("Kalesma gia paroxi nerou");
    }


}

