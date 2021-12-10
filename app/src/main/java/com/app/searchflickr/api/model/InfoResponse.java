package com.app.searchflickr.api.model;

import com.google.gson.annotations.SerializedName;

public class InfoResponse {

    @SerializedName("photo")
    private InfoResponsePhoto photo;

    public InfoResponsePhoto getPhoto() {
        return photo;
    }

    public void setPhoto(InfoResponsePhoto photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "InfoResponse{" +
                "photo=" + photo +
                '}';
    }
}
