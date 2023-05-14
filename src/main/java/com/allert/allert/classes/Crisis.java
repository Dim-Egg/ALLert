package com.allert.allert.classes;

public class Crisis {
    private String place;
    private String description;
    private Level importance;

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

    public Crisis(String place, String description, Level importance) {
        this.place = place;
        this.description = description;
        this.importance = importance;
    }
}

enum Level {
    LOW,
    MEDIUM,
    HIGH
}
