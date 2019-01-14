package com.example.mohamdkazem.advancetodolist.Model;

public class Users {
    private String name;
    private String password;
    private String email;

    public int getmId() {
        return mId;
    }

    private int mId;



    public Users(String userName, String passWord, String email) {
        this.name = userName;
        this.password = passWord;
        this.email = email;

    }

    public Users(String name, String password, String email, int mId) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.mId = mId;
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


}
