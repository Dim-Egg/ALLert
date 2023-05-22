package com.allert.allert.classes;

public class Economic_Help extends Help{
    public Economic_Help(String description, Economic_Item[] item_list) {
        super(description, item_list);
    }
    @Override
    public Economic_Item[] getItem_list() {
        return (Economic_Item[]) super.getItem_list();
    }
}
