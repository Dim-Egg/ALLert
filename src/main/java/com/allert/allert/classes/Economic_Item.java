package com.allert.allert.classes;

public class Economic_Item extends Item{
    private String link;

    public Economic_Item(String name, String link) {
        super(name);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
