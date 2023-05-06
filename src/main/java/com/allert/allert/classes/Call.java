package com.allert.allert.classes;

public class Call {
    private Entity entity;
    private Crisis crisis;
    private Help[] help_List;
    private String date;

    public Call(Entity entity, Crisis crisis, Material_Help material_Help, Volunteer_Help volunteer_Help, Economic_Help economic_Help, String date) {
        this.entity = entity;
        this.crisis = crisis;
        this.help_List = new Help[]{material_Help, volunteer_Help, economic_Help};
        this.date = date;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Crisis getCrisis() {
        return crisis;
    }

    public void setCrisis(Crisis crisis) {
        this.crisis = crisis;
    }

    public Help[] getHelp_List() {
        return help_List;
    }

    public void setHelp_List(Help[] help_List) {
        this.help_List = help_List;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
