package com.allert.allert.graphs;

import com.allert.allert.classes.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class callPane extends AnchorPane {

    public callPane(Call displayCall){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/callPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
            innerCallDescription.setText(displayCall.getDescription());
            innerCallDate.setText(displayCall.getDate());
            criLink.setText(displayCall.getCrisis().getName());
            forLink.setText(displayCall.getEntity().getName());

            for(Material_Item matItem:((Material_Help)displayCall.getHelp_List()[0]).getItem_list()){
                eidosPane newItem = new eidosPane(Integer.toString(matItem.getNeeded_Quantity()),Integer.toString(matItem.getAccumulated_Quantity()),matItem.getName());
                matHelp.getChildren().add(newItem);
            }
            for(Volunteer_Item volItem:((Volunteer_Help)displayCall.getHelp_List()[1]).getItem_list()){
                eidosPane newItem = new eidosPane(Integer.toString(volItem.getNeeded_Force()),Integer.toString(volItem.getAccumulated_Force()),volItem.getName());
                volHelp.getChildren().add(newItem);
            }
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
    public VBox ecHelp;

    @FXML
    public Hyperlink criLink;
    @FXML
    public Hyperlink forLink;

}
