package com.allert.allert.graphs;

import com.allert.allert.VollunteerInitialContoller;
import com.allert.allert.classes.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class callPane extends AnchorPane {
    public int callId;
    @FXML
    public Label status;
    public callPane(Call displayCall,Volunteer volunteer){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/callPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        callId = displayCall.getId();
        try {
            loader.load();
            innerCallDescription.setText(displayCall.getDescription());
            innerCallDate.setText(displayCall.getDate());
            criLink.setText(displayCall.getCrisis().getName());
            forLink.setText(displayCall.getEntity().getName());


            if(!Respond.hasResponded(volunteer,displayCall)) {
                if(!displayCall.getHelp_List()[0].isEmpty())
                    for (Material_Item matItem : ((Material_Help) displayCall.getHelp_List()[0]).getItem_list()) {
                        eidosPane newItem = new eidosPane(Integer.toString(matItem.getNeeded_Quantity()), Integer.toString(matItem.getAccumulated_Quantity()), matItem.getName());
                        matHelp.getChildren().add(newItem);
                    }
                if(!displayCall.getHelp_List()[1].isEmpty())
                    for (Volunteer_Item volItem : ((Volunteer_Help) displayCall.getHelp_List()[1]).getItem_list()) {
                        eidosPane newItem = new eidosPane(Integer.toString(volItem.getNeeded_Force()), Integer.toString(volItem.getAccumulated_Force()), volItem.getName());
                        volHelp.getChildren().add(newItem);
                    }

            }else{
                Respond respond = Respond.respondList.stream().filter(respond1 -> respond1.getCall().equals(displayCall)&&respond1.getVolunteer().equals(volunteer)).toList().get(0);
                status.setText(respond.getState().toString());
                if(respond.getHelp_list()[0].isEmpty()){
                    matHelp.getChildren().add(new Label("EMPTY"));
                }else{
                    Arrays.stream(((Material_Help)respond.getHelp_list()[0]).getItem_list()).toList().forEach(materialItem -> {
                        eidosPane newItem = new eidosPane("0",Integer.toString(materialItem.getAccumulated_Quantity()), materialItem.getName());
                        newItem.needAmount.setVisible(false);
                        newItem.accAmount.setLayoutX(115);
                        matHelp.getChildren().add(newItem);
                    });
                }
                if(respond.getHelp_list()[1].isEmpty()){
                    volHelp.getChildren().add(new Label("EMPTY"));
                }else{
                    Arrays.stream(((Volunteer_Help)respond.getHelp_list()[1]).getItem_list()).toList().forEach(volunteerItem -> {
                        eidosPane newItem = new eidosPane("0","Applied", volunteerItem.getName());
                        newItem.needAmount.setVisible(false);
                        newItem.accAmount.setLayoutX(115);
                        newItem.accAmount.setPrefWidth(90);
                        volHelp.getChildren().add(newItem);
                    });
                }
            }
            if(!displayCall.getHelp_List()[2].isEmpty())
            for(Economic_Item ecItem:((Economic_Help)displayCall.getHelp_List()[2]).getItem_list()){
                Hyperlink newItem = new Hyperlink(ecItem.getName());
                newItem.setOnAction(e->{
                    try {
                        Desktop.getDesktop().browse(new URI(ecItem.getLink()));
                    } catch (IOException | URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                ecHelp.getChildren().add(newItem);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public TextField innerCallDate;
    @FXML
    public TextArea innerCallDescription;
    @FXML
    public VBox matHelp;

    @FXML
    public VBox volHelp;

    @FXML
    public Button helpButton;

    @FXML
    public VBox ecHelp;

    @FXML
    public Hyperlink criLink;
    @FXML
    public Hyperlink forLink;

    public callPane(Entity displayEntity) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/callPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
            innerCallDescription.setText(displayEntity.getDescription());
            innerCallDate.setText(displayEntity.getTelephone() + " " + displayEntity.getEmail());
            criLink.setText("Calls");

            callOff();


        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    public callPane(Crisis displayCrisis) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/callPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
            innerCallDescription.setText(displayCrisis.getDescription());
            criLink.setText("Calls");
            forLink.setText("Foreis pou Voithoun");

            callOff();


        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    public void callOff(){

        this.getChildren().removeAll(
                this.getChildren().stream().filter(node -> ("callContent".equals(node.getId()))).toList());
    }
}
