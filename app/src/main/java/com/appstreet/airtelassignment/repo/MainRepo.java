package com.appstreet.airtelassignment.repo;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import com.appstreet.airtelassignment.data.api.ApiInterface;
import com.appstreet.airtelassignment.data.api.RetrofitClient;
import com.appstreet.airtelassignment.data.datamodel.DevAssets;
import com.appstreet.airtelassignment.utils.ResponseStatus;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class MainRepo {

    private ApiInterface mApiInterface;

    @Inject
    public MainRepo(ApiInterface apiInterface) {
        this.mApiInterface = apiInterface;
    }

    private MutableLiveData<List<DevAssets>> devAssetsLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseStatus> responseStatusLiveData = new MutableLiveData<>();

    public void getDevAssets(){
        responseStatusLiveData.setValue(ResponseStatus.LOADING);
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        apiInterface.getDevAssets().enqueue(new Callback<List<DevAssets>>() {
            @Override
            public void onResponse(Call<List<DevAssets>> call, Response<List<DevAssets>> response) {
                Log.d("DEV_DATA", response.body().get(0).getRepo().getName());
                devAssetsLiveData.setValue(response.body());
                responseStatusLiveData.setValue(ResponseStatus.SUCCESS);
            }

            @Override
            public void onFailure(Call<List<DevAssets>> call, Throwable t) {
                responseStatusLiveData.setValue(ResponseStatus.ERROR);
            }
        });
    }

    public MutableLiveData<List<DevAssets>> getAllDevAssets(){
        return devAssetsLiveData;
    }

    public MutableLiveData<ResponseStatus> getResponseStatus() {
        return responseStatusLiveData;
    }

}
