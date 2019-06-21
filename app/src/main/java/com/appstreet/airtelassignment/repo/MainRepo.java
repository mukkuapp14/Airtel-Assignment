package com.appstreet.airtelassignment.repo;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import com.appstreet.airtelassignment.data.api.ApiInterface;
import com.appstreet.airtelassignment.data.api.RetrofitClient;
import com.appstreet.airtelassignment.data.datamodel.DevAssets;
import com.appstreet.airtelassignment.views.MainActivity;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class MainRepo {

    private ApiInterface mApiInterface;

    @Inject
    public MainRepo(ApiInterface apiInterface) {
        this.mApiInterface = apiInterface;
    }

    private MutableLiveData<List<DevAssets>> devAssetsLiveData = new MutableLiveData<>();

    public void getDevAssets(){
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        apiInterface.getDevAssets().enqueue(new Callback<List<DevAssets>>() {
            @Override
            public void onResponse(Call<List<DevAssets>> call, Response<List<DevAssets>> response) {
                Log.d("DEV_DATA", response.body().get(0).getRepo().getName());
                devAssetsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<DevAssets>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<DevAssets>> getAllDevAssets(){
        return devAssetsLiveData;
    }

}
