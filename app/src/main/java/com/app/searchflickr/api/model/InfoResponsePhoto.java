package com.app.searchflickr.api.model;

import com.google.gson.annotations.SerializedName;

public class InfoResponsePhoto {

    @SerializedName("owner")
    private InfoResponsePhotoOwner owner;
    @SerializedName("title")
    private InfoResponsePhotoTitle title;
    @SerializedName("description")
    private InfoResponsePhotoDescription description;
    @SerializedName("dates")
    private InfoResponsePhotoDates dates;

    public InfoResponsePhotoOwner getOwner() {
        return owner;
    }

    public void setOwner(InfoResponsePhotoOwner owner) {
        this.owner = owner;
    }

    public InfoResponsePhotoTitle getTitle() {
        return title;
    }

    public void setTitle(InfoResponsePhotoTitle title) {
        this.title = title;
    }

    public InfoResponsePhotoDescription getDescription() {
        return description;
    }

    public void setDescription(InfoResponsePhotoDescription description) {
        this.description = description;
    }

    public InfoResponsePhotoDates getDates() {
        return dates;
    }

    public void setDates(InfoResponsePhotoDates dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "InfoResponsePhoto{" +
                "owner=" + owner +
                ", title=" + title +
                ", description=" + description +
                ", dates=" + dates +
                '}';
    }
}
