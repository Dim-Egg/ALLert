package com.allert.allert.graphs;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddItem extends AnchorPane {
    public TextField Name;
    public TextField needAmount;
    public Button minusButton;
    public Button plusButton;

    public AddItem (){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/addItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();


        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    public AddItem (boolean empty){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/addEconomicItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();


        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
