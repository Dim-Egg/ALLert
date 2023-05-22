package com.allert.allert.classes;

public class Volunteer_Help extends Help{
    private String instructions;

    public Volunteer_Help(String description, Volunteer_Item[] item_list, String instructions) {
        super(description, item_list);
        this.instructions = instructions;
    }
    @Override
    public Volunteer_Item[] getItem_list() {
        return (Volunteer_Item[]) super.getItem_list();
    }
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
