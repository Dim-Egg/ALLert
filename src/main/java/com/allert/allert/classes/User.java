package com.allert.allert.classes;

public class User {
    protected String name;
    protected String password;
    protected String email;
    protected String telephone;

    public User(String name, String password, String email, String telephone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
    }

    public User(String name, String email, String telephone) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
