package com.appstreet.airtelassignment.data.api;

import com.appstreet.airtelassignment.data.datamodel.DevAssets;
import com.appstreet.airtelassignment.utils.Endpoints;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET(Endpoints.DEV_ASSETS)
    Call<List<DevAssets>> getDevAssets();

}
