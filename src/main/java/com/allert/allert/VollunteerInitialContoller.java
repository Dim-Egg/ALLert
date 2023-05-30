package com.allert.allert;

import com.allert.allert.classes.*;
import com.allert.allert.graphs.HelpPane;
import com.allert.allert.graphs.callPane;
import com.allert.allert.graphs.contentPane;
import com.allert.allert.graphs.eidosPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.sound.sampled.AudioInputStream;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class VollunteerInitialContoller {

    public VBox leftTab;

    public static User currentUser;

    public TitledPane rightTitle;
    public Button searchButton;
    public TextField searchField;
    public Button callMenu;
    public Button orgMenu;
    public Button criMenu;
    public Button aitimaButton;

    public static Stage secondaryWindow;
    public String filterWord = "";
    public Button pendButton;
    public Button apprButton;
    public Button conButton;
    public CheckBox responses;

    public VollunteerInitialContoller vollunteerInitialContoller;
    public Button logOutButton;

    @FXML
    protected void initialize() {
        vollunteerInitialContoller = this;

        logOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("dummyLogIn.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 600, 400);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage mainWindow = MainApplication.mainWindow;
                mainWindow.setTitle("Login");
                mainWindow.setResizable(false);
                mainWindow.setScene(scene);
                mainWindow.centerOnScreen();
            }
        });
        responses.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldvalue, Boolean newvalue) {
                if(newvalue){
                    criMenu.setVisible(false);
                    orgMenu.setVisible(false);
                    callMenu.setVisible(false);
                    pendButton.setVisible(true);
                    apprButton.setVisible(true);
                    conButton.setVisible(true);

                    searchField.setDisable(true);
                    searchButton.setDisable(true);

                    addAllResponds(State.PENDING);

                }else{
                    criMenu.setVisible(true);
                    orgMenu.setVisible(true);
                    callMenu.setVisible(true);
                    pendButton.setVisible(false);
                    apprButton.setVisible(false);
                    conButton.setVisible(false);

                    searchField.setDisable(false);
                    searchButton.setDisable(false);

                    addAllCalls();
                }
            }
        });

        pendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addAllResponds(State.PENDING);
            }
        });
        apprButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addAllResponds(State.APPROVED);
            }
        });;
        conButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addAllResponds(State.CONFIRMED);
            }
        });;

        searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent actionEvent) {
                if(actionEvent.getCode().equals(KeyCode.ENTER)){
                    filterWord = searchField.getText();
                    if(callMenu.isDefaultButton())
                        addAllCalls();
                    if(criMenu.isDefaultButton())
                        addAllCrisis();
                    if(orgMenu.isDefaultButton())
                        addAllOrgs();

                }
            }
        });
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                filterWord = searchField.getText();
                if(callMenu.isDefaultButton())
                    addAllCalls();
                if(criMenu.isDefaultButton())
                    addAllCrisis();
                if(orgMenu.isDefaultButton())
                    addAllOrgs();
                
            }
        });
        callMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addAllCalls();
            }
        });
        criMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addAllCrisis();
            }
        });
        orgMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addAllOrgs();
            }
        });

        aitimaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("aitima-view.fxml"));
                Scene scene;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                secondaryWindow = new Stage();
                secondaryWindow.setTitle("Aitima Anagkis");

                secondaryWindow.setScene(scene);
                secondaryWindow.show();
                secondaryWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent windowEvent) {
                        MainApplication.mainWindow.show();
                    }
                });
                MainApplication.mainWindow.hide();
            }
        });

        addAllCalls();


    }

    private void addAllResponds(State state) {

        if(state.equals(State.PENDING)){
            pendButton.setDefaultButton(true);
            apprButton.setDefaultButton(false);
            conButton.setDefaultButton(false);
        }
        else if(state.equals(State.APPROVED)){
            pendButton.setDefaultButton(false);
            apprButton.setDefaultButton(true);
            conButton.setDefaultButton(false);
        }
        else if(state.equals(State.CONFIRMED)){
            pendButton.setDefaultButton(false);
            apprButton.setDefaultButton(false);
            conButton.setDefaultButton(true);
        }

        leftTab.getChildren().clear();

        Respond.respondList.stream().filter(respond -> respond.getVolunteer().equals(currentUser)).filter(respond -> respond.getState().equals(state))
                .toList().forEach(respond -> {
            Call call = respond.getCall();

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


    public ArrayList<String> append(String word, ArrayList<Integer> list){
        ArrayList<String> finalList = new ArrayList<String>();
        for (int i : list)
            finalList.add(word+Integer.toString(i));

        return finalList;
    }

    public ArrayList<String> appendString(String word, ArrayList<String> list){
        ArrayList<String> finalList = new ArrayList<String>();
        for (String i : list)
            finalList.add(word+i);
        return finalList;
    }

    public void setRightTab(String type,String id){
        if(type.equals("Call") ) {
            Call displayCall = Call.findById(Integer.parseInt(id));
            rightTitle.setText(displayCall.getTitle());
            callPane callPane = new callPane(displayCall,(Volunteer)currentUser);
            rightTitle.setContent(callPane);

            callPane.criLink.setOnAction(e->{
                filterWord = callPane.criLink.getText();

                    addAllCrisis();
                setRightTab("Crisis", callPane.criLink.getText());

            });

            callPane.forLink.setOnAction(e->{
                filterWord = callPane.forLink.getText();

                addAllOrgs();
                setRightTab("Entity", callPane.forLink.getText());

            });

            if(Respond.hasResponded((Volunteer) currentUser,Call.findById(callPane.callId))){
                 callPane.helpButton.setText("Cancel Help");
                callPane.helpButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you Sure you want to cancel your help?");
                        if(alert.showAndWait().get() == ButtonType.OK){
                            Respond.respondList.remove(Respond.getRespond((Volunteer) currentUser,displayCall));
                            vollunteerInitialContoller.empty();}
                    }});;
            }
            else
                callPane.helpButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    HelpPane helpPane = new HelpPane((Volunteer) currentUser,Call.findById(callPane.callId),vollunteerInitialContoller);
                    Scene scene;
                    Group root = new Group(helpPane);
                    scene = new Scene(root, 600, 400);
                    secondaryWindow = new Stage();
                    secondaryWindow.setTitle("Help "+displayCall.getTitle());

                    secondaryWindow.setScene(scene);
                    secondaryWindow.show();
                    MainApplication.mainWindow.hide();

                }
            });
        }
        if(type.equals("Entity") ) {
            Entity displayEntity = Entity.findByName(id);
            rightTitle.setText(displayEntity.getName());
            callPane callPane = new callPane(displayEntity);
            rightTitle.setContent(callPane);

            callPane.criLink.setOnAction(e->{
                filterWord = rightTitle.getText();

                addAllCalls();


            });

        }
        if(type.equals("Crisis")){
            Crisis displayCrisis = Crisis.findByName(id);
            rightTitle.setText(displayCrisis.getName());
            callPane callPane = new callPane(displayCrisis);
            rightTitle.setContent(callPane);

            callPane.criLink.setOnAction(e->{
                filterWord = rightTitle.getText();

                addAllCalls();


            });
            callPane.forLink.setOnAction(e->{
                filterWord = rightTitle.getText();

                addAllOrgs();


            });
        }
    }

    public void empty(){
        addAllCalls();
        rightTitle.setContent(null);
        rightTitle.setText("");
    }
    public void addAllCalls(){

        callMenu.setDefaultButton(true);
        criMenu.setDefaultButton(false);
        orgMenu.setDefaultButton(false);

        leftTab.getChildren().clear();
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
            if(Respond.hasResponded((Volunteer) currentUser,call))
                newItem.status.setText(Respond.getRespond((Volunteer) currentUser,call).getState().toString());
        });

        if(!filterWord.equals("")){
        ArrayList<String> searchedCalls = append("Call",Call.search(filterWord));
        leftTab.getChildren().removeAll(
                leftTab.getChildren().stream().filter(node -> node instanceof Pane&&!(searchedCalls.contains(node.getId()))).toList());}

    }

    public void addAllOrgs(){
        callMenu.setDefaultButton(false);
        criMenu.setDefaultButton(false);
        orgMenu.setDefaultButton(true);
        leftTab.getChildren().clear();

        Entity.entityList.forEach((entity) -> {

            contentPane newItem = new contentPane(entity.getDescription(),
                    entity.getPlace(), entity.getName(),"Entity",entity.getName());
            newItem.contentButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setRightTab("Entity", entity.getName());
                }
            });
            leftTab.getChildren().add(newItem);
        });

        if(!filterWord.equals("")){
            ArrayList<String> searched = appendString("Entity",Entity.search(filterWord));
            leftTab.getChildren().removeAll(
                    leftTab.getChildren().stream().filter(node -> node instanceof Pane&&!(searched.contains(node.getId()))).toList());}

    }

    public void addAllCrisis(){
        callMenu.setDefaultButton(false);
        criMenu.setDefaultButton(true);
        orgMenu.setDefaultButton(false);
        leftTab.getChildren().clear();

        Crisis.crisisList.forEach((crisis) -> {

            contentPane newItem = new contentPane(crisis.getDescription(),
                    crisis.getPlace(), crisis.getName(),"Crisis",crisis.getName());
            newItem.contentButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setRightTab("Crisis", crisis.getName());
                }
            });
            leftTab.getChildren().add(newItem);
        });

        if(!filterWord.equals("")){
            ArrayList<String> searched = appendString("Crisis",Crisis.search(filterWord));
            leftTab.getChildren().removeAll(
                    leftTab.getChildren().stream().filter(node -> node instanceof Pane&&!(searched.contains(node.getId()))).toList());}
    }
}

