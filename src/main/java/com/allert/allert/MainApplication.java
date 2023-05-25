package com.allert.allert;

import com.allert.allert.classes.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public static Stage mainWindow;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Volunteer-initial-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        mainWindow = stage;
        mainWindow.setTitle("Volunteer");


        Image image = new Image("https://raw.githubusercontent.com/Dim-Egg/ALLert/e709813c7db573e9e26d13f88693ee46b5c51121/ALLert.png");
        //stage.getIcons().add(image);
        mainWindow.setScene(scene);
        mainWindow.show();

    }

    public static void main(String[] args) {
        dummyData();launch();
    }
    public static void dummyData(){

        new Crisis("Fotia Evias",
                "Evia",
                "Megali fotia stin perioxi tis evias 10000 dasikon stremmaton",
                Crisis.Level.MEDIUM);

        new Entity("Stavros",
                "emrwinr213491",
                "helpdesk@stavros.org",
                "21033445566",
                "Athens",
                "Medical help in places of need",
                "IMAGE");
        new Entity("Pirosvestiki dinami peiraia",
                "emrwinr213491",
                "helpdesk@pirpirea.org",
                "21063524156",
                "Athens",
                "Emergency help in wildfires and extreme situations",
                "IMAGE");

        new Call("Voithia stin fotia Evias apo Stavro",
                "Os stavros parexoume voithia tiw teleutaiew dio evdomades stin megali fotia stin evia, xreiazomaste farmaka kai iatriko prosopiko gia na enisxithoun oi prospatheies mas",
                Entity.findByName("Stavros"), Crisis.findByName("Fotia Evias"),
                new Material_Help("Anagki gia farmaka", new Material_Item[]{
                        new Material_Item("Depon",100,0),
                        new Material_Item("Iodio",150,0)},
                        "farasalon 15, Karistos"),
                new Volunteer_Help("Anagki gia nosileutes kai psihologous", new Volunteer_Item[]{
                        new Volunteer_Item("Nosileutes",15,0),
                        new Volunteer_Item("Psihologoi",5,0)}
                        ,"farasalon 15, Karistos stin Aspri polikatikia"),
                new Economic_Help("Anagki gia oikonomiki enishisi ton pligenton", new Economic_Item[]{
                        new Economic_Item("Oikonomiki enishisi episitismou","dorees.org/stavros/evia/episitismos"),
                        new Economic_Item("Oikonomiki enishisi estiasis","dorees.org/stavros/evia/estiasi")}
                        ),
                "13/07/2023");

        new Call("Voithia stin fotia Evias apo Piroscestiki",
                "Os Pirosvestiki parexoume voithia apo tin arxi stin fotia stin evia, xreiazomaste ethelontes kai dorees gia na sinexisoyms",
                Entity.findByName("Pirosvestiki dinami peiraia"), Crisis.findByName("Fotia Evias"),
                new Material_Help(),
                new Volunteer_Help("Anagki gia odiogus kai pirosvestes", new Volunteer_Item[]{
                        new Volunteer_Item("odigos",20,0),
                        new Volunteer_Item("pirosvestis",50,0)}
                        ,"stin arxi tou dasous stin kokkini tenta"),
                new Economic_Help("Anagki gia oikonomiki enishisi ton ethelonton", new Economic_Item[]{
                        new Economic_Item("Oikonomiki enishisi episitismou ethelonton","dorees.org/pirosvestes/evia/episitismos"),
                        new Economic_Item("Oikonomiki enishisi kausimon","dorees.org/pirosvestes/evia/kausima")}
                ),
                "10/07/2023");
    }
}
