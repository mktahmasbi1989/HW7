package com.example.mohamdkazem.advancetodolist.Model;

import android.content.Intent;

import java.util.UUID;

public class Users {
    private String name;
    private String password;
    private String email;
    private int userId;

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
    public int getUserId() {
        return userId;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Users(String name, String password, String email, int id) {
        this.name = name;
        this.password = password;
        this.email = email;
        userId=id;
    }

    public Users(String name, String password,String emial) {
        this.name = name;
        this.password = password;
        this.email=emial;
    }
}
