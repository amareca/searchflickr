package com.app.searchflickr.api;

import com.app.searchflickr.api.model.InfoResponse;
import com.app.searchflickr.api.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {

    @GET("rest/?method=flickr.photos.search&api_key=4aa665647d8e4d18219b94194454a9d5&format=json&nojsoncallback=1")
    public Call<SearchResponse> getData(@Query("tags")String tags);

    @GET("rest/?method=flickr.photos.getInfo&api_key=4aa665647d8e4d18219b94194454a9d5&format=json&nojsoncallback=1")
    public Call<InfoResponse> getInfoByPhotoId(@Query("photo_id")String id);

}
