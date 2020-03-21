package com.example.logindemo.Model;

public class User {
    public User(String id, String username, String imageurl) {
        this.id = id;
        this.username = username;
        this.imageurl = imageurl;
    }

    private String id;

    public User() {

    }

    private String username;
    private String imageurl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


}
