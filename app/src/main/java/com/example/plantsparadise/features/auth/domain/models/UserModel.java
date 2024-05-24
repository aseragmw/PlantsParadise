package com.example.plantsparadise.features.auth.domain.models;

public class UserModel {
    private String uid;
    private String username;
    private String email;

    public String getUid() {
        return uid;
    }

    public UserModel(String uid, String username, String email) {
        this.uid = uid;
        this.username = username;
        this.email = email;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
