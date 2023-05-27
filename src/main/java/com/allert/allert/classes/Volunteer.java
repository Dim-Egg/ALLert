package com.allert.allert.classes;

public class Volunteer extends User{
    private String place;
    private Skill[] skills;
    private boolean availability;


    public Volunteer(String name, String password, String email, String telephone,String place, Skill[] skills, boolean availability) {
        super(name,password,email,telephone);
        this.place = place;
        this.skills = skills;
        this.availability = availability;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}

