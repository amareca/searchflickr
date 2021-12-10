package com.app.searchflickr.api.model;

import com.google.gson.annotations.SerializedName;

public class InfoResponsePhotoDates {

    @SerializedName("taken")
    private String taken;

    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    @Override
    public String toString() {
        return "InfoResponsePhotoDates{" +
                "taken='" + taken + '\'' +
                '}';
    }
}
