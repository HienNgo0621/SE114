package com.example.myapplication;

import java.io.Serializable;

public class Post implements Serializable {
    private String authorName;
    private String date;
    private String content;
    private String avtURL;

    public Post(String authorName, String date, String content, String avatarUrl) {
        this.authorName = authorName;
        this.date = date;
        this.content = content;
        this.avtURL = avtURL;
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

    public String getAvatarUrl() {
        return avtURL;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avtURL = avatarUrl;
    }
}
