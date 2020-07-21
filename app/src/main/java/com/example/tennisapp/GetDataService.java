package com.example.tennisapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/match")
    Call<List<JsonData>> getJsonData();

}
