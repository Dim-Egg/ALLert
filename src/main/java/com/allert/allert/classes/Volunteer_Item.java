package com.allert.allert.classes;

public class Volunteer_Item extends Item{
    private int needed_Force;
    private int accumulated_Force;

    public Volunteer_Item(String name, int needed_Force, int accumulated_Force) {
        super(name);
        this.needed_Force = needed_Force;
        this.accumulated_Force = accumulated_Force;
    }

    public int getNeeded_Force() {
        return needed_Force;
    }

    public void setNeeded_Force(int needed_Force) {
        this.needed_Force = needed_Force;
    }

    public int getAccumulated_Force() {
        return accumulated_Force;
    }

    public void setAccumulated_Force(int accumulated_Force) {
        this.accumulated_Force = accumulated_Force;
    }


}
