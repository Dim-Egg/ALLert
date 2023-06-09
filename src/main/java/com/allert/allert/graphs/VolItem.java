package com.allert.allert.graphs;

import com.allert.allert.classes.Material_Item;
import com.allert.allert.classes.Respond;
import com.allert.allert.classes.Volunteer_Item;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VolItem extends TitledPane {
    public VBox paneContainer;

    public List<VolunteerSelectionPane> volunteerSelectionPaneList;

    public int acc;
    public Volunteer_Item item;

    public Material_Item matItem;

    public VolItem(Volunteer_Item item){
        this.item = item;
        acc=item.getAccumulated_Force();
        volunteerSelectionPaneList = new ArrayList<>();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/volItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();


        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.setText(item.getName()+" "+acc+"/"+item.getNeeded_Force());

    }

    public VolItem(Material_Item item) {
        this.matItem = item;
        acc=item.getAccumulated_Quantity();
        volunteerSelectionPaneList = new ArrayList<>();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/allert/allert/volItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();


        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.setText(item.getName()+" "+acc+"/"+item.getNeeded_Quantity());
    }

    public void increase(){
        this.setText(item.getName()+" "+(++acc)+"/"+item.getNeeded_Force());

    }

    public void increase(int amount){
        acc+=amount;
        this.setText(matItem.getName()+" "+(acc)+"/"+matItem.getNeeded_Quantity());

    }
    public void decrease(){
        this.setText(item.getName()+" "+(--acc)+"/"+item.getNeeded_Force());

    }

    public void decrease(int amount){
        acc-=amount;
        this.setText(matItem.getName()+" "+(acc)+"/"+matItem.getNeeded_Quantity());

    }

    public void add(VolunteerSelectionPane pane){
        paneContainer.getChildren().add(pane);
        volunteerSelectionPaneList.add(pane);
    }



}
