package com.allert.allert.classes;

import java.util.ArrayList;
import java.util.List;

public class Crisis {
    private String name;
    private String place;
    private String description;
    private Level importance;
    public static List<Crisis> crisisList = new ArrayList<>();

    public static Crisis findByName(String name){
        return (Crisis) crisisList.stream().filter(crisis -> name.equals(crisis.name)).toList().get(0);
    }

    public static ArrayList<String> search(String word){
        ArrayList<String> idList = new ArrayList<String>();
        for(Crisis crisis: crisisList){
            if(crisis.name.contains(word)||crisis.description.contains(word)||crisis.place.contains(word)||crisis.importance.toString().contains(word))
                    idList.add(crisis.name);
        }
        return idList;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level getImportance() {
        return importance;
    }

    public void setImportance(Level importance) {
        this.importance = importance;
    }

    public Crisis(String name, String place, String description, Level importance) {
        this.name = name;
        this.place = place;
        this.description = description;
        this.importance = importance;
        crisisList.add(this);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public static enum Level {
        LOW,
        MEDIUM,
        HIGH
    }
}


