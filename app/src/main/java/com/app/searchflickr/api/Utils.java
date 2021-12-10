package com.app.searchflickr.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.flickr.com/services/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static String getImageFromData(String id, String idSecret, String farmId, String serverId) {
        return "https://farm" + farmId + ".staticflickr.com/" + serverId + "/" + id + "_" + idSecret + ".jpg";
    }
}
