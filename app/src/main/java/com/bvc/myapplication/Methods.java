package com.bvc.myapplication;

import com.bvc.myapplication.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {

    @GET("?apikey=b9bd48a6&s=Marvel&type=movie")
    Call<Movie> getAllData();

}
