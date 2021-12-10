package com.app.searchflickr.api.model;

import com.google.gson.annotations.SerializedName;

public class InfoResponsePhotoOwner {

    @SerializedName("username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "InfoResponsePhotoOwner{" +
                "username='" + username + '\'' +
                '}';
    }
}
