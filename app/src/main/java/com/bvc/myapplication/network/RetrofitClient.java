package com.bvc.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    public static Retrofit getRetrofitInstance(String title,String type) {
        String BASE_URL = "https://www.omdbapi.com/";
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
