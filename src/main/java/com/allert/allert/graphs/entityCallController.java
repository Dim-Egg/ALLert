package com.allert.allert.graphs;

import com.allert.allert.EntityInitialController;
import com.allert.allert.MainApplication;
import com.allert.allert.VollunteerInitialContoller;
import com.allert.allert.classes.Crisis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class entityCallController {
    public TextArea innerCallDescription;
    public VBox matHelp;
    public VBox volHelp;
    public VBox ecHelp;
    public Button saveButton;
    public Button cancelButton;
    public TextField titleField;
    public Button plusMatButton;
    public Button plusVolButton;
    public Button plusEcButton;
    public Button editMatButton;
    public Button editVolButton;
    public Button editEcButton;
    public Button respondMatButton;
    public Button respondVolButton;
    public ChoiceBox criChoise;

    @FXML
    protected void initialize(){
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(
                Crisis.crisisList.stream().map(Crisis::getName).collect(Collectors.toList()));
        criChoise.setItems(items);

        plusMatButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AddItem newItem = new AddItem();
                matHelp.getChildren().add(newItem);
                newItem.minusButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        matHelp.getChildren().remove(newItem);
                    }
                });
                newItem.plusButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(newItem.Name.getText().equals("")||newItem.needAmount.getText().equals("")){
                            Alert alert = new Alert(Alert.AlertType.ERROR,"The Item Can't be null");
                            alert.show();
                            return;
                        }
                        try {
                            Integer.parseInt(newItem.needAmount.getText());
                        }catch (NumberFormatException exception){
                            Alert alert = new Alert(Alert.AlertType.ERROR,"The amount has to be a number");
                            alert.show();
                            return;
                        }
                        if(Integer.parseInt(newItem.needAmount.getText())<0){
                            Alert alert = new Alert(Alert.AlertType.ERROR,"The amount has to be positive");
                            alert.show();
                            return;
                        }
                        eidosPane eidosItem = new eidosPane(newItem.needAmount.getText(), "0", newItem.Name.getText());
                        matHelp.getChildren().add(eidosItem);
                        matHelp.getChildren().remove(newItem);
                    }
                });
            }
        });

        plusVolButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AddItem newItem = new AddItem();
                volHelp.getChildren().add(newItem);
                newItem.minusButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        volHelp.getChildren().remove(newItem);
                    }
                });
                newItem.plusButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(newItem.Name.getText().equals("")||newItem.needAmount.getText().equals("")){
                            Alert alert = new Alert(Alert.AlertType.ERROR,"The Item Can't be null");
                            alert.show();
                            return;
                        }
                        try {
                            Integer.parseInt(newItem.needAmount.getText());
                        }catch (NumberFormatException exception){
                            Alert alert = new Alert(Alert.AlertType.ERROR,"The amount has to be a number");
                            alert.show();
                            return;
                        }
                        if(Integer.parseInt(newItem.needAmount.getText())<0){
                            Alert alert = new Alert(Alert.AlertType.ERROR,"The amount has to be positive");
                            alert.show();
                            return;
                        }
                        eidosPane eidosItem = new eidosPane(newItem.needAmount.getText(), "0", newItem.Name.getText());
                        volHelp.getChildren().add(eidosItem);
                        volHelp.getChildren().remove(newItem);
                    }
                });
            }
        });

        plusEcButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AddItem newItem = new AddItem(true);
                ecHelp.getChildren().add(newItem);
                newItem.minusButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        ecHelp.getChildren().remove(newItem);
                    }
                });
                newItem.plusButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(newItem.Name.getText().equals("")||newItem.needAmount.getText().equals("")){
                            Alert alert = new Alert(Alert.AlertType.ERROR,"The Item Can't be null");
                            alert.show();
                            return;
                        }


                        Hyperlink eidosItem = new Hyperlink(newItem.Name.getText());
                        eidosItem.setOnAction(e->{
                            try {
                                Desktop.getDesktop().browse(new URI(newItem.needAmount.getText()));
                            } catch (IOException | URISyntaxException ex) {
                                throw new RuntimeException(ex);
                            }
                        });

                        ecHelp.getChildren().add(eidosItem);
                        ecHelp.getChildren().remove(newItem);
                    }
                });
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EntityInitialController.secondaryWindow.close();
                MainApplication.mainWindow.show();
            }
        });
    }

}
