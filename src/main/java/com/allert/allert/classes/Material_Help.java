package com.allert.allert.classes;

public class Material_Help extends Help{
    private String instructions;

    public Material_Help(String description, Material_Item[] item_list, String instructions) {
        super(description, item_list);
        this.instructions = instructions;
    }

    public Material_Help() {
        super();
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
