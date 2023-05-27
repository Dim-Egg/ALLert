package com.allert.allert.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    protected String name;
    protected String password;
    protected String email;
    protected String telephone;
    protected int id;
    protected static int curid = 0;
    public static List<User> usersList = new ArrayList<>();
    public int getId() {
        return id;
    }

    public static User getUserById(int id){
        return usersList.stream().filter(user -> (user.id==id)).toList().get(0);
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String name, String password, String email, String telephone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.id = curid++;
        usersList.add(this);
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
