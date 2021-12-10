package com.app.searchflickr.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponsePhotos {

    @SerializedName("photo")
    private List<SearchResponsePhoto> photoList;

    public List<SearchResponsePhoto> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<SearchResponsePhoto> photoList) {
        this.photoList = photoList;
    }

    @Override
    public String toString() {
        return "SearchResponsePhotos{" +
                "photoList=" + photoList +
                '}';
    }
}
