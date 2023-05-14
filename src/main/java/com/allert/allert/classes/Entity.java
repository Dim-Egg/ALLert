package com.allert.allert.classes;

public class Entity extends User{
    private String place;

    public Entity(String name, String password, String email, String telephone, String place, String description, String logo) {
        super(name, password, email, telephone);
        this.place = place;
        this.description = description;
        this.logo = logo;
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
