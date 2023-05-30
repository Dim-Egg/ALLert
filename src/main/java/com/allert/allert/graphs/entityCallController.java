package com.allert.allert.graphs;

import com.allert.allert.EntityInitialController;
import com.allert.allert.MainApplication;
import com.allert.allert.VollunteerInitialContoller;
import com.allert.allert.classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class entityCallController extends AnchorPane {
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
    public Button respondMatButton;
    public Button respondVolButton;
    public ChoiceBox criChoise;

    public String matIstructions = "";

    public String volIstructions= "";

    public static EntityInitialController entityInitialController = null;

    public entityCallController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/entityCall.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        Scene scene;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EntityInitialController.secondaryWindow = new Stage();
        EntityInitialController.secondaryWindow.setTitle("New Call!");

        EntityInitialController.secondaryWindow.setScene(scene);
        EntityInitialController.secondaryWindow.show();
        EntityInitialController.secondaryWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                MainApplication.mainWindow.show();
            }
        });
        MainApplication.mainWindow.hide();


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

                        HBox item = new HBox();
                        Button minus = new Button("-");
                        eidosItem.setPrefWidth(250);
                        minus.setPrefHeight(45);
                        minus.setPrefHeight(45);
                        item.getChildren().addAll(eidosItem,minus);

                        minus. setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                matHelp.getChildren().remove(item);
                            }});

                        matHelp.getChildren().add(item);
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

                        HBox item = new HBox();
                        Button minus = new Button("-");
                        eidosItem.setPrefWidth(250);
                        minus.setPrefHeight(45);
                        minus.setPrefHeight(45);
                        item.getChildren().addAll(eidosItem,minus);

                        minus. setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                volHelp.getChildren().remove(item);
                            }});

                        volHelp.getChildren().add(item);
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
                        eidosItem.setId(newItem.needAmount.getText());
                        eidosItem.setOnAction(e->{
                            try {
                                Desktop.getDesktop().browse(new URI(newItem.needAmount.getText()));
                            } catch (IOException | URISyntaxException ex) {
                                throw new RuntimeException(ex);
                            }
                        });

                        HBox item = new HBox();
                        Button minus = new Button("-");
                        eidosItem.setPrefWidth(250);
                        minus.setPrefHeight(45);
                        minus.setPrefHeight(10);
                        item.getChildren().addAll(eidosItem,minus);

                        minus. setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                ecHelp.getChildren().remove(item);
                            }});

                        ecHelp.getChildren().add(item);
                        ecHelp.getChildren().remove(newItem);
                    }
                });
            }
        });

        editMatButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                EditItemController editItem = new EditItemController("Edit Material Instructions");

                editItem.instructions.setText(matIstructions);
                editItem.saveButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(editItem.instructions.getText().equals("")){
                            Alert alert = new Alert(Alert.AlertType.ERROR,"Your Instructions Can't be null");
                            alert.show();
                            return;
                        }
                        matIstructions = editItem.instructions.getText();

                        editItem.close();

                    }
                });

                editItem.show();

            }
        });

        editVolButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                EditItemController editItem = new EditItemController("Edit Volunteer Instructions");

                editItem.instructions.setText(volIstructions);
                editItem.saveButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if(editItem.instructions.getText().equals("")){
                            Alert alert = new Alert(Alert.AlertType.ERROR,"Your Instructions Can't be null");
                            alert.show();
                            return;
                        }
                        volIstructions = editItem.instructions.getText();

                        editItem.close();

                    }
                });

                editItem.show();

            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(innerCallDescription.equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"You didn't described the new call");
                    alert.show();return;
                }

                if(titleField.equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"You didn't titled the new call");
                    alert.show();return;
                }

                if(criChoise.getSelectionModel().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"You didn't picked a crisis");
                    alert.show();return;
                }

                if(matHelp.getChildren().isEmpty()&&volHelp.getChildren().isEmpty()&&ecHelp.getChildren().isEmpty())
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR,"You didn't register any help");
                    alert.show();return;}

                Material_Help material_help = new Material_Help("", new Material_Item[]{},"");
                if(!matHelp.getChildren().stream().filter(node -> node instanceof HBox).toList().isEmpty()){
                    if(matIstructions.equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Your Instruction for the material help Can't be null press edit to add it");
                    alert.show();return;}else{
                        material_help.setInstructions(matIstructions);
                        List<Material_Item> itemList= new ArrayList<>();
                        matHelp.getChildren().stream().map(node -> ((HBox)node).getChildren().get(0)).forEach(node -> {
                            if(node instanceof eidosPane){
                                eidosPane eidos = (eidosPane) node;
                                Material_Item item = new Material_Item(eidos.name.getText(),Integer.parseInt(eidos.needAmount.getText()),Integer.parseInt(eidos.accAmount.getText()));
                                itemList.add(item);
                            }
                        });
                        material_help.setItem_list(itemList.toArray(new Material_Item[0]));
                    }}
                Volunteer_Help volunteer_help = new Volunteer_Help("", new Volunteer_Item[]{},"");
                if(!volHelp.getChildren().stream().filter(node -> node instanceof HBox).toList().isEmpty()){
                    if(volIstructions.equals("")){
                        Alert alert = new Alert(Alert.AlertType.ERROR,"Your Instruction for the material help Can't be null press edit to add it");
                        alert.show();return;}else{
                        volunteer_help.setInstructions(volIstructions);
                        List<Volunteer_Item> itemList= new ArrayList<>();
                        volHelp.getChildren().stream().map(node -> ((HBox)node).getChildren().get(0)).forEach(node -> {
                            if(node instanceof eidosPane){
                                eidosPane eidos = (eidosPane) node;
                                Volunteer_Item item = new Volunteer_Item(eidos.name.getText(),Integer.parseInt(eidos.needAmount.getText()),Integer.parseInt(eidos.accAmount.getText()));
                                itemList.add(item);
                            }
                        });
                        volunteer_help.setItem_list(itemList.toArray(new Volunteer_Item[0]));
                    }}

                Economic_Help economic_help = new Economic_Help("", null);
                if(!ecHelp.getChildren().stream().filter(node -> node instanceof HBox).toList().isEmpty()){

                        List<Economic_Item> itemList= new ArrayList<>();
                        ecHelp.getChildren().stream().map(node -> ((HBox)node).getChildren().get(0)).forEach(node -> {
                            if(node instanceof Hyperlink){
                                Hyperlink eidos = (Hyperlink) node;
                                Economic_Item item = new Economic_Item(eidos.getText(),eidos.getId());
                                itemList.add(item);
                            }
                        });
                    economic_help.setItem_list(itemList.toArray(new Economic_Item[0]));
                    }


                new Call(titleField.getText(),
                        innerCallDescription.getText(),
                        EntityInitialController.currentUser,
                        Crisis.findByName(criChoise.getSelectionModel().getSelectedItem().toString()),
                      material_help,volunteer_help,economic_help,  (new SimpleDateFormat("dd/MM/yyyy")).format (new Date())
                );

                entityInitialController.addCalls();

                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Saved!");
                EntityInitialController.secondaryWindow.close();
                MainApplication.mainWindow.show();
                alert.show();
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
