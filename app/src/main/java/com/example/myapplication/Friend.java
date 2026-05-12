package com.example.myapplication;

public class Friend {
    private String name;
    private String avtURL;

    public Friend(String name, String avtURL) {
        this.name = name;
        this.avtURL = avtURL;
    }

    public String getName() { return name; }
    public String getAvtURL() { return avtURL; }
}