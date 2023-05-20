package com.allert.allert.classes;

import java.util.ArrayList;
import java.util.List;

public class Call {
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Entity entity;
    private Crisis crisis;
    private Help[] help_List;
    private String date;
    private int id;

    public int getId() {
        return id;
    }
    public static Call findById(int id){
        return callsList.stream().filter(call -> call.id == id).toList().get(0);
    }


    private static int itId = 0;

    public static List<Call> callsList = new ArrayList<>();
    public Call(String title, String description, Entity entity, Crisis crisis, Material_Help material_Help, Volunteer_Help volunteer_Help, Economic_Help economic_Help, String date) {
        this.title = title;
        this.description = description;
        this.entity = entity;
        this.crisis = crisis;
        this.help_List = new Help[]{material_Help, volunteer_Help, economic_Help};
        this.date = date;
        callsList.add(this);
        this.id = itId++;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Crisis getCrisis() {
        return crisis;
    }

    public void setCrisis(Crisis crisis) {
        this.crisis = crisis;
    }

    public Help[] getHelp_List() {
        return help_List;
    }

    public void setHelp_List(Help[] help_List) {
        this.help_List = help_List;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
