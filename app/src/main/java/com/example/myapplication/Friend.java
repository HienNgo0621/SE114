package com.example.myapplication;

public class Friend {
    private String name;
    private int imageRes;

    public Friend(String name, int imageRes) {
        this.name = name;
        this.imageRes = imageRes;
    }

    public String getName() { return name; }
    public int getImageRes() { return imageRes; }
}