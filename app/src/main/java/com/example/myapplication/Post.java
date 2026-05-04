package com.example.myapplication;

import java.io.Serializable;

public class Post implements Serializable {
    private String authorName;
    private String date;
    private String content;
    private int imageRes;

    public Post(String authorName, String date, String content, String avatarUrl) {
        this.authorName = authorName;
        this.date = date;
        this.content = content;
        this.imageRes = imageRes;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAvatarUrl() {
        return imageRes;
    }

    public void setAvatarUrl(int avatarUrl) {
        this.imageRes = avatarUrl;
    }
}
