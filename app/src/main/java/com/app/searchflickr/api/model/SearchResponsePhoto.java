package com.app.searchflickr.api.model;

import com.google.gson.annotations.SerializedName;

public class SearchResponsePhoto {

    @SerializedName("id")
    private String id;
    @SerializedName("secret")
    private String secret;
    @SerializedName("owner")
    private String owner;
    @SerializedName("server")
    private String server;
    @SerializedName("farm")
    private String farm;
    @SerializedName("title")
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SearchResponsePhoto{" +
                "id='" + id + '\'' +
                ", secret='" + secret + '\'' +
                ", owner='" + owner + '\'' +
                ", server='" + server + '\'' +
                ", farm='" + farm + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
