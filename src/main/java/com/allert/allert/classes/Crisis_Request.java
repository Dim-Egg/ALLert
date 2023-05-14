package com.allert.allert.classes;

public class Crisis_Request extends Request {
    private String place;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Crisis_Request(User user, String description, String crisis) {
        super(user,description);
        this.place = crisis;
    }
}
