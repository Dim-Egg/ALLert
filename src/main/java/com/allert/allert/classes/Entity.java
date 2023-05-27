package com.allert.allert.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Entity extends User{
    private String place;
    public static List<Entity> entityList = new ArrayList<>();
    public Entity(String name, String password, String email, String telephone, String place, String description, String logo) {
        super(name, password, email, telephone);
        this.place = place;
        this.description = description;
        this.logo = logo;
        entityList.add(this);
    }

    public static Entity findByName(String name){
        return entityList.stream().filter(entity -> name.equals(entity.name)).toList().get(0);
    }

    public static ArrayList<String> search(String word){
        ArrayList<String> idList = new ArrayList<String>();
        for(Entity enitty: entityList){
            if(enitty.name.contains(word)||enitty.description.contains(word)||enitty.email.contains(word)||enitty.telephone.contains(word)||enitty.place.contains(word))//||enitty.representative.getName().contains(word))
                idList.add(enitty.name);
        }
        return idList;
    }
    private String description;
    private String logo;
    private User representative;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public User getRepresentative() {
        return representative;
    }

    public void setRepresentative(String name, String email, String telephone) {
        this.representative = new User(name,email,telephone);
    }
}
