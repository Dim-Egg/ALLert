package com.allert.allert.classes;

public class Material_Item extends Item{
    private int needed_Quantity;
    private int accumulated_Quantity;

    public Material_Item(String name, int needed_Quantity, int accumulated_Quantity) {
        super(name);
        this.needed_Quantity = needed_Quantity;
        this.accumulated_Quantity = accumulated_Quantity;
    }

    public int getNeeded_Quantity() {
        return needed_Quantity;
    }

    public void setNeeded_Quantity(int needed_Quantity) {
        this.needed_Quantity = needed_Quantity;
    }

    public int getAccumulated_Quantity() {
        return accumulated_Quantity;
    }

    public void setAccumulated_Quantity(int accumulated_Quantity) {
        this.accumulated_Quantity = accumulated_Quantity;
    }
}
