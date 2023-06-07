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
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("dummyLogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainWindow = stage;
        mainWindow.setTitle("Login");
        mainWindow.setResizable(false);


        Image image = new Image("https://raw.githubusercontent.com/Dim-Egg/ALLert/e709813c7db573e9e26d13f88693ee46b5c51121/ALLert.png");
        //stage.getIcons().add(image);
        mainWindow.setScene(scene);
        mainWindow.show();

    }
    public static void logIn(int id) throws IOException {
        User user = User.getUserById(id);
        if(user instanceof Volunteer){
            VollunteerInitialContoller.currentUser = user;
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Volunteer-initial-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
            mainWindow.setTitle(user.getName());

            mainWindow.setScene(scene);
            mainWindow.centerOnScreen();
        }
        else if(user instanceof Entity){
            EntityInitialController.currentUser = (Entity) user;
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Entity-initial-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
            mainWindow.setTitle(user.getName());
            mainWindow.setScene(scene);
            mainWindow.centerOnScreen();

        }
    }
    public static void main(String[] args) {
        dummyData();launch();
    }
    public static void dummyData(){

        new Volunteer("Fotis papas",
                "emrwinr213491",
                "papafotis@gmail.com",
                "6900800899",
                "Patra",new Skill[]{Skill.DRIVING},true);
        new Volunteer("Marika Karalida",
                "emrwinr213491",
                "karalida@outlook.com",
                "6900345345",
                "Thiva",new Skill[]{Skill.COOKING},false);
        new Volunteer("Mitsos Karanikas",
                "emrwinr213491",
                "karanika@outlook.com",
                "69363636985",
                "Siros",new Skill[]{Skill.DRIVING,Skill.COOKING},false);
        new Volunteer("Lamprini thoma",
                "emrwinr213491",
                "tom@outlook.com",
                "6356251452",
                "Athens",new Skill[]{Skill.COOKING},true);

        new Crisis("Fotia Evias",
                "Evia",
                "Megali fotia stin perioxi tis evias 10000 dasikon stremmaton",
                Crisis.Level.MEDIUM);

        new Crisis("Kima Metanaston stin siro",
                "Siros",
                "Polloi Metanastes stin siro",
                Crisis.Level.LOW);

        new Entity("Paralegal NGO",
                "emrwinr213491",
                "helpdesk@parango.org",
                "2635122225",
                "Thessaloniki",
                "Legal help in places of need",
                "IMAGE");

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
                "28/05/2023");
        new Call("Nomiki Voithtia stous metanastes",
                "Os Paralegals parexoume nomiki voithia se anthropous me anagki kai sto kima metanaston polloi synathropoi mas xreiazontai thn voitheia maw",
                Entity.findByName("Paralegal NGO"),Crisis.findByName("Kima Metanaston stin siro"),
                new Material_Help("Anagki gia stegasi tiw omadas", new Material_Item[]{
                        new Material_Item("Spiti",1,0),
                        new Material_Item("Ylika Grafeioy",25,0)},
                        "Parte mas sto tilefono mas"),
                new Volunteer_Help("Anagki gia nomikous kai psihologous", new Volunteer_Item[]{
                        new Volunteer_Item("Nomikoi",15,0),
                        new Volunteer_Item("Psihologoi",5,0)}
                        ,"Sto Camp ton metanaston meta apo sinnneoisi tilefoniki"),
                new Economic_Help("Anagki gia oikonomiki enishisi ton exodon mas", new Economic_Item[]{
                        new Economic_Item("Oikonomiki enishisi metakinisis","dorees.org/paralegal/siros/metakinisi"),
                        new Economic_Item("Oikonomiki enishisi diamonis","dorees.org/paralegal/siros/diamoni")}
                ),
                "07/06/2023");

        new Call("Iatriki Voithtia stous metanastes",
                "Os Stavros parexoume iatriki voithia se anthropous me anagki kai sto kima metanaston polloi synathropoi mas xreiazontai thn voitheia maw",
                Entity.findByName("Stavros"),Crisis.findByName("Kima Metanaston stin siro"),
                new Material_Help("Anagki gia Gazes kai depon", new Material_Item[]{
                        new Material_Item("Gazes",50,0),
                        new Material_Item("Depon",25,0)},
                        "Filelinon 5, siros"),
                new Volunteer_Help("Anagki gia koinonikous leitoyrgous", new Volunteer_Item[]{
                        new Volunteer_Item("Koin. Leitourgoi",7,0),}
                                                ,"Filelinon 5, siros"),
                new Economic_Help("Anagki gia oikonomiki enishisi ton exodon mas", new Economic_Item[]{
                        new Economic_Item("Oikonomiki enishisi asthenoforou","dorees.org/stavros/siros/asthenoforo"),
                        }
                ),
                "03/06/2023");


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
