package com.app.searchflickr.api.model;

import com.google.gson.annotations.SerializedName;

public class SearchResponse {

    @SerializedName("photos")
    private SearchResponsePhotos photos;

    public SearchResponsePhotos getPhotos() {
        return photos;
    }

    public void setPhotos(SearchResponsePhotos photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "photos=" + photos +
                '}';
    }
}
