package com.app.searchflickr.api.model;

import com.google.gson.annotations.SerializedName;

public class InfoResponsePhotoDescription {

    @SerializedName("_content")
    private String _content;

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }

    @Override
    public String toString() {
        return "InfoResponsePhotoDescription{" +
                "_content='" + _content + '\'' +
                '}';
    }
}
