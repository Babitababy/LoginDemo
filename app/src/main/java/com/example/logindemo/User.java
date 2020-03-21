package com.example.logindemo;

public class User {
    public User() {
    }

    public User(String username, String id, String imageurl) {
        this.username = username;
        this.id = id;
        this.imageurl = imageurl;
    }

    private String username;
    private String id;
    private String imageurl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


}
