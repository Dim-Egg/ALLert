package com.allert.allert.graphs;

import com.allert.allert.MainApplication;
import com.allert.allert.VollunteerInitialContoller;
import com.allert.allert.classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.stream.Collectors;

public class HelpPane extends AnchorPane {

    public Button okButton;
    public VBox matHelp;
    public VBox volHelp;
    public Button cancelButton;


    public HelpPane(Volunteer volunteer, Call call){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/helpPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();

            for(Material_Item matItem:((Material_Help)call.getHelp_List()[0]).getItem_list()){
                eidosPane newItem = new eidosPane(Integer.toString(matItem.getNeeded_Quantity()),Integer.toString(matItem.getAccumulated_Quantity()),matItem.getName());
                matHelp.getChildren().add(newItem);
                newItem.accAmount.setEditable(true);
            }
            for(Volunteer_Item volItem:((Volunteer_Help)call.getHelp_List()[1]).getItem_list()){
                eidosPane newItem = new eidosPane(Integer.toString(volItem.getNeeded_Force()),Integer.toString(volItem.getAccumulated_Force()),volItem.getName());
                volHelp.getChildren().add(newItem);
                newItem.needAmount.setEditable(true);
            }

                    cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            VollunteerInitialContoller.secondaryWindow.close();
                            MainApplication.mainWindow.show();
                        }
                    });

                    okButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        matHelp.getChildren().forEach( node->{
                            if(node instanceof Pane){
                                TextField accNode = (TextField) ((Pane) node).getChildren().stream().filter(amount->"accAmount".equals(amount.getId())).toList().get(0);
                                int regamount = Integer.parseInt(accNode.getText());

                                if(!(regamount == 0)){

                                TextField nedNode = (TextField) ((Pane) node).getChildren().stream().filter(amount->"nedAmount".equals(amount.getId())).toList().get(0);
                                int nedAmount = Integer.parseInt(nedNode.getText());
                                if(regamount>nedAmount){
                                    Alert alert = new Alert(Alert.AlertType.ERROR,"You chose more than you should!");
                                    alert.show();}
                                }
                            }
                        });

//                        boolean in = false;
//                        if(criChoises.getSelectionModel().isEmpty()){
//                            Alert alert = new Alert(Alert.AlertType.ERROR,"You must choose a Crisis");
//                            alert.show();
//                            in = true;
//                        }
//                        if(aitimaText.getText().equals("")){
//                            Alert alert = new Alert(Alert.AlertType.ERROR,"Your Description Can't be null");
//                            alert.show();
//                            in = true;
//                        }
//                        if(in)return;
//                        new Need_Request(VollunteerInitialContoller.currentUser,aitimaText.getText(), Crisis.findByName(criChoises.getSelectionModel().getSelectedItem().toString()));
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Saved!");
//                        alert.show();
//                        VollunteerInitialContoller.secondaryWindow.close();
//                        MainApplication.mainWindow.show();
                    }
                });

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

//    @FXML
//    protected void initialize() {
//        okButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                boolean in = false;
//                if(criChoises.getSelectionModel().isEmpty()){
//                    Alert alert = new Alert(Alert.AlertType.ERROR,"You must choose a Crisis");
//                    alert.show();
//                    in = true;
//                }
//                if(aitimaText.getText().equals("")){
//                    Alert alert = new Alert(Alert.AlertType.ERROR,"Your Description Can't be null");
//                    alert.show();
//                    in = true;
//                }
//                if(in)return;
//                new Need_Request(VollunteerInitialContoller.currentUser,aitimaText.getText(), Crisis.findByName(criChoises.getSelectionModel().getSelectedItem().toString()));
//                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Saved!");
//                alert.show();
//                VollunteerInitialContoller.secondaryWindow.close();
//                MainApplication.mainWindow.show();
//            }
//        });
//        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                VollunteerInitialContoller.secondaryWindow.close();
//                MainApplication.mainWindow.show();
//            }
//        });
//        ObservableList<String> items = FXCollections.observableArrayList();
//        items.addAll(
//                Crisis.crisisList.stream().map(Crisis::getName).collect(Collectors.toList()));
//        criChoises.setItems(items);
//    }
}
