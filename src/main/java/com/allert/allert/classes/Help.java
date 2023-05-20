package com.allert.allert.classes;

public class Help {
    protected String description;
    protected Item[] item_list;
    protected boolean empty = false;

    public boolean isEmpty() {
        return empty;
    }

    public Help(String description, Item[] item_list) {
        this.description = description;
        this.item_list = item_list;
    }

    public Help() {
        empty = true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item[] getItem_list() {
        return item_list;
    }

    public void setItem_list(Item[] item_list) {
        this.item_list = item_list;
    }
}
