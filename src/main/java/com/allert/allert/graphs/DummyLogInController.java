package com.allert.allert.graphs;

import com.allert.allert.classes.Entity;
import com.allert.allert.classes.User;
import com.allert.allert.classes.Volunteer;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class DummyLogInController {

    public VBox Entities;
    public VBox Volunteers;
    @FXML
    protected void initialize(){
        User.usersList.forEach(user->{
            if(user instanceof Volunteer){
                Volunteers.getChildren().add(new loginPane((Volunteer)user));
            } else if (user instanceof Entity) {
                Entities.getChildren().add(new loginPane((Entity)user));
            }
        });
    }

}
