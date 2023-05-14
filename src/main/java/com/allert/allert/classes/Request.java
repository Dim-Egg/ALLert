package com.allert.allert.classes;

public class Request {
    private User user;
    private String Description;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Request(User user, String description) {
        this.user = user;
        Description = description;
    }
}

