package com.allert.allert.graphs;

import com.allert.allert.EntityInitialController;
import com.allert.allert.MainApplication;
import com.allert.allert.classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
import java.util.concurrent.atomic.AtomicBoolean;
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

    public EntityInitialController entityInitialController = null;

    public entityCallController(Call call, EntityInitialController entityInitialController, Volunteer user){
        this.entityInitialController = entityInitialController;
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

        if(call!=null) {
            titleField.setText(call.getTitle());
            innerCallDescription.setText(call.getDescription());
            criChoise.getSelectionModel().select(call.getCrisis().getName());


            if(!call.getHelp_List()[0].isEmpty()){
                matIstructions = ((Material_Help)call.getHelp_List()[0]).getInstructions();
                for (Material_Item materialItem : (Material_Item[]) call.getHelp_List()[0].getItem_list()) {
                eidosPane eidosItem = new eidosPane(Integer.toString(materialItem.getNeeded_Quantity()), Integer.toString(materialItem.getAccumulated_Quantity()), materialItem.getName());
                eidosItem.needAmount.setEditable(true);
                HBox item = new HBox();
                Button minus = new Button("-");
                eidosItem.setPrefWidth(250);
                minus.setPrefHeight(45);
                minus.setPrefHeight(45);
                item.getChildren().addAll(eidosItem, minus);

                minus.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        matHelp.getChildren().remove(item);
                    }
                });

                matHelp.getChildren().add(item);
            }}
            if(!((Volunteer_Help)call.getHelp_List()[1]).isEmpty()){
                volIstructions = ((Volunteer_Help)call.getHelp_List()[1]).getInstructions();
                for (Volunteer_Item volunteerItem : (Volunteer_Item[]) call.getHelp_List()[1].getItem_list()) {
                    eidosPane eidosItem = new eidosPane(Integer.toString(volunteerItem.getNeeded_Force()), Integer.toString(volunteerItem.getAccumulated_Force()), volunteerItem.getName());
                    eidosItem.needAmount.setEditable(true);
                    HBox item = new HBox();
                    Button minus = new Button("-");
                    eidosItem.setPrefWidth(250);
                    minus.setPrefHeight(45);
                    minus.setPrefHeight(45);
                    item.getChildren().addAll(eidosItem, minus);

                    minus.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            volHelp.getChildren().remove(item);
                        }
                    });

                    volHelp.getChildren().add(item);
                }}
            if(!((Economic_Help)call.getHelp_List()[2]).isEmpty()){
                for (Economic_Item economicItem : (Economic_Item[]) call.getHelp_List()[2].getItem_list()) {

                    Hyperlink eidosItem = new Hyperlink(economicItem.getName());
                    eidosItem.setId(economicItem.getLink());
                    eidosItem.setOnAction(e->{
                        try {
                            Desktop.getDesktop().browse(new URI(economicItem.getLink()));
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
                }}
        }

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
                        eidosItem.needAmount.setEditable(true);
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
                        eidosItem.needAmount.setEditable(true);
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

        if(!Respond.respondList.stream().filter(respond -> respond.getCall().equals(call)&&!respond.getHelp_list()[1].isEmpty()).toList().isEmpty()){
        respondVolButton.setVisible(true);
            respondVolButton.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent actionEvent) {
                    VolSelection selection = new VolSelection(call,false);

                    selection.show();
               }
           });
        }

        if(!Respond.respondList.stream().filter(respond -> respond.getCall().equals(call)&&!respond.getHelp_list()[0].isEmpty()).toList().isEmpty()){
            respondMatButton.setVisible(true);
            respondMatButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    VolSelection selection = new VolSelection(call,true);

                    selection.show();
                }
            });
        }

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

                Material_Help material_help = new Material_Help();
                if(!matHelp.getChildren().stream().filter(node -> node instanceof HBox).toList().isEmpty()){
                    if(matIstructions.equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Your Instruction for the material help Can't be null press edit to add it");
                    alert.show();return;}else{
                        material_help = new Material_Help(null,null,null);
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
                Volunteer_Help volunteer_help = new Volunteer_Help();
                if(!volHelp.getChildren().stream().filter(node -> node instanceof HBox).toList().isEmpty()){
                    if(volIstructions.equals("")){
                        Alert alert = new Alert(Alert.AlertType.ERROR,"Your Instruction for the material help Can't be null press edit to add it");
                        alert.show();return;}else{
                        volunteer_help =new Volunteer_Help(null,null,null);
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

                Economic_Help economic_help = new Economic_Help();
                if(!ecHelp.getChildren().stream().filter(node -> node instanceof HBox).toList().isEmpty()){
                        economic_help = new Economic_Help(null,null);
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

                if(call!=null){


                    Volunteer_Help finalVolunteer_help = volunteer_help;
                    AtomicBoolean returnNow = new AtomicBoolean(false);
                    AtomicBoolean in = new AtomicBoolean(false);
                    AtomicBoolean finalIn1 = in;
                    AtomicBoolean finalReturnNow1 = returnNow;
                    Respond.respondList.stream().filter(respond -> respond.getCall().equals(call)&&!respond.getHelp_list()[1].isEmpty()).toList().forEach(respond -> {

                                for (Item item : respond.getHelp_list()[1].getItem_list()) {
                                    if(((Volunteer_Item)item).getNeeded_Force() == 1){{
                                        for (Volunteer_Item volunteerItem : finalVolunteer_help.getItem_list()) {
                                            if(item.getName().equals(volunteerItem.getName())){
                                                Alert alert = new Alert(Alert.AlertType.ERROR,"There are still responds that you need to manage");
                                                alert.show();
                                                finalReturnNow1.set(true);
                                            }
                                        }

                                    }} else if (((Volunteer_Item)item).getNeeded_Force() == 0) {
                                        respond.setState(State.APPROVED);
                                        finalIn1.set(true);
                                    }
                                }
                                if(!finalIn1.get()){
                                    respond.setState(State.PENDING);
                                }

                            });

                    if(finalReturnNow1.get())
                        return;

                    Material_Help finalMaterialHelp = material_help;
                    returnNow = new AtomicBoolean(false);
                    in = new AtomicBoolean(false);
                    AtomicBoolean finalReturnNow = returnNow;
                    AtomicBoolean finalIn = in;
                    Respond.respondList.stream().filter(respond -> respond.getCall().equals(call)&&!respond.getHelp_list()[0].isEmpty()).toList().forEach(respond -> {

                        for (Item item : respond.getHelp_list()[0].getItem_list()) {
                            if(((Material_Item)item).getNeeded_Quantity() == 0){{
                                for (Material_Item materialItem : finalMaterialHelp.getItem_list()) {
                                    if(item.getName().equals(materialItem.getName())){
                                        Alert alert = new Alert(Alert.AlertType.ERROR,"There are still responds that you need to manage");
                                        alert.show();
                                        finalReturnNow.set(true);
                                    }
                                }

                            }} else if (((Material_Item)item).getNeeded_Quantity() > 0) {
                                respond.setState(State.APPROVED);
                                finalIn.set(true);
                            }
                        }
                        if(!finalIn.get()){
                            respond.setState(State.PENDING);
                        }

                    });

                    if(finalReturnNow.get())
                        return;




                    call.update(titleField.getText(),
                            innerCallDescription.getText(),
                            EntityInitialController.currentUser,
                            Crisis.findByName(criChoise.getSelectionModel().getSelectedItem().toString()),
                            material_help,volunteer_help,economic_help,  (new SimpleDateFormat("dd/MM/yyyy")).format (new Date()));
                }else {
                    Call call = new Call(titleField.getText(),
                            innerCallDescription.getText(),
                            EntityInitialController.currentUser,
                            Crisis.findByName(criChoise.getSelectionModel().getSelectedItem().toString()),
                            material_help, volunteer_help, economic_help, (new SimpleDateFormat("dd/MM/yyyy")).format(new Date())
                    );
                    if(user != null)
                        new Notification(user,call);
                }

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
