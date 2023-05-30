package com.allert.allert.graphs;

import com.allert.allert.MainApplication;
import com.allert.allert.VollunteerInitialContoller;
import com.allert.allert.classes.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class HelpPane extends AnchorPane {

    public Button okButton;
    public VBox matHelp;
    public VBox volHelp;
    public Button cancelButton;


    public HelpPane(Volunteer volunteer, Call call, VollunteerInitialContoller vollunteerInitialContoller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/helpPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            ArrayList<eidosPane> matNodes = new ArrayList<>();
            ArrayList<eidosPane> volNodes = new ArrayList<>();
            if(!call.getHelp_List()[0].isEmpty())
                for(Material_Item matItem:((Material_Help)call.getHelp_List()[0]).getItem_list()){
                    if(matItem.getNeeded_Quantity()-matItem.getAccumulated_Quantity()>0) {
                        eidosPane newItem = new eidosPane(Integer.toString(matItem.getNeeded_Quantity() - matItem.getAccumulated_Quantity()), "0", matItem.getName());
                        matHelp.getChildren().add(newItem);
                        newItem.accAmount.setEditable(true);
                        matNodes.add(newItem);
                    }
                }
            else
                matHelp.getChildren().add(new Label("Empty"));
            if(!call.getHelp_List()[1].isEmpty())
                for(Volunteer_Item volItem:((Volunteer_Help)call.getHelp_List()[1]).getItem_list()){
                    if(volItem.getNeeded_Force()-volItem.getAccumulated_Force()>0) {
                        eidosPane newItem = new eidosPane(volItem.getName());
                        volHelp.getChildren().add(newItem);
                        newItem.needAmount.setEditable(true);
                        volNodes.add(newItem);
                    }

                }
            else
                volHelp.getChildren().add(new Label("Empty"));

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

                        ArrayList<Material_Item> offeredMatHelp = new ArrayList<>();
                        matNodes.forEach(matNode->{
                            int accAmount = Integer.parseInt(matNode.accAmount.getText());
                            int nedAmount = Integer.parseInt(matNode.needAmount.getText());
                            if(accAmount!=0){
                                if(accAmount>nedAmount||accAmount<0){
                                    Alert alert = new Alert(Alert.AlertType.ERROR,"Wrong values in "+matNode.name.getText());
                                    alert.show();
                                    return;
                                }
                                offeredMatHelp.add(new Material_Item(matNode.name.getText(),0,accAmount));
                            }
                        });

                        ArrayList<Volunteer_Item> offereVoldHelp = new ArrayList<>();
                        volNodes.forEach(volNode->{

                            if(volNode.yesButton.isDefaultButton()){

                                offereVoldHelp.add(new Volunteer_Item(volNode.name.getText(),1,0));
                            }
                        });

                        if(offereVoldHelp.isEmpty()&&offeredMatHelp.isEmpty()){
                            Alert alert = new Alert(Alert.AlertType.ERROR,"You didn't helped :(, please reenter values");
                            alert.show();
                            return;
                        } else if(offereVoldHelp.isEmpty()){
                            new Respond(volunteer,call,State.PENDING,new Material_Help((String) null, offeredMatHelp.toArray(new Material_Item[0]),null),new Volunteer_Help(),null);
                        }
                        else if(offeredMatHelp.isEmpty()){
                            new Respond(volunteer,call,State.PENDING,new Material_Help(),new Volunteer_Help((String) null, offereVoldHelp.toArray(new Volunteer_Item[0]),null),null);
                        }else{
                            new Respond(volunteer,call,State.PENDING,new Material_Help((String) null, offeredMatHelp.toArray(new Material_Item[0]),null),
                                    new Volunteer_Help((String) null, offereVoldHelp.toArray(new Volunteer_Item[0]),null),null);
                        }

                        VollunteerInitialContoller.secondaryWindow.close();
                        MainApplication.mainWindow.show();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Saved!");
                        alert.show();
                        vollunteerInitialContoller.empty();
                    }
                });

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
