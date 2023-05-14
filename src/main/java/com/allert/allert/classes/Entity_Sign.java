package com.allert.allert.classes;

public class Entity_Sign {
    private Entity entity;
    private String sign_Text;

    public Entity_Sign(Entity entity, String sign_Text) {
        this.entity = entity;
        this.sign_Text = sign_Text;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getSign_Text() {
        return sign_Text;
    }

    public void setSign_Text(String sign_Text) {
        this.sign_Text = sign_Text;
    }
}
