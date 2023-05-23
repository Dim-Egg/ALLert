package com.allert.allert;

import com.allert.allert.classes.*;
import com.allert.allert.graphs.callPane;
import com.allert.allert.graphs.contentPane;
import com.allert.allert.graphs.eidosPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VollunteerInitialContoller {

    public VBox leftTab;



    public TitledPane rightTitle;
    public Button searchButton;
    public TextField searchField;



    @FXML
    protected void initialize() {
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                search(searchField.getText());
            }
        });
        addAllCalls();


    }

    public void search(String word){
        leftTab.getChildren().clear();
        addAllCalls();
        searchField.setText("");
        ArrayList<String> searchedCalls = append("Call",Call.search(word));
        ArrayList<Node> removedNodes = new ArrayList<Node>();
        for(Node node : leftTab.getChildren())
            if(node instanceof Pane)
                if (!searchedCalls.contains(node.getId()))
                    removedNodes.add(node);
        leftTab.getChildren().removeAll(removedNodes);


    }

    public ArrayList<String> append(String word, ArrayList<Integer> list){
        ArrayList<String> finalList = new ArrayList<String>();
        for (int i : list)
            finalList.add(word+Integer.toString(i));

        return finalList;
    }

    public void setRightTab(String type,String id){
        if(type.equals("Call") ) {
            Call displayCall = Call.findById(Integer.parseInt(id));
            rightTitle.setText(displayCall.getTitle());
            rightTitle.setContent(new callPane(displayCall));

        }
    }
    public void addAllCalls(){
        Call.callsList.forEach((call) -> {

            contentPane newItem = new contentPane(call.getDescription(),
                    call.getCrisis().getName()+" "+call.getDate(),call.getTitle(),"Call",Integer.toString(call.getId()));
            newItem.contentButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setRightTab("Call",Integer.toString(call.getId()));
                }
            });
            leftTab.getChildren().add(newItem);
        });

    }

}

