package com.allert.allert.classes;

import java.util.ArrayList;
import java.util.List;

public class Respond{
    private Volunteer volunteer;
    private Call call;
    private State state;
    private Help[] help_List;
    public static List<Respond> respondList = new ArrayList<>();
    public Respond(Volunteer volunteer, Call call, State state, Material_Help material_Help, Volunteer_Help volunteer_Help, Economic_Help economic_Help) {
        this.volunteer = volunteer;
        this.call = call;
        this.state = state;
        this.help_List = new Help[]{material_Help, volunteer_Help, economic_Help};
        respondList.add(this);
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Help[] getHelp_list() {
        return help_List;
    }

    public void setHelp_list(Help[] help_List) {
        this.help_List = help_List;
    }
}

enum State {
    PENDING,
    CONFIRMED,
    COMPLETED
}