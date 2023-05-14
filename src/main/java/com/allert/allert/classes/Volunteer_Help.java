package com.allert.allert.classes;

public class Volunteer_Help extends Help{
    private String instructions;

    public Volunteer_Help(String description, Volunteer_Item[] item_list, String instructions) {
        super(description, item_list);
        this.instructions = instructions;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
