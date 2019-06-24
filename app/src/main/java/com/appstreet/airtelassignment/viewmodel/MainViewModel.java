package com.appstreet.airtelassignment.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.appstreet.airtelassignment.App;
import com.appstreet.airtelassignment.data.datamodel.DevAssets;
import com.appstreet.airtelassignment.repo.MainRepo;
import com.appstreet.airtelassignment.utils.ResponseStatus;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {
    public LiveData<List<DevAssets>> devAssetsLiveData;
    public LiveData<ResponseStatus> responseStatusLiveData;
    private App app;

    @Inject
    public MainRepo mainRepo;

    public MainViewModel() {
        app = new App();
        app.getAppComponent().inject(this);
        this.devAssetsLiveData = getAllassests();
        this.responseStatusLiveData = mainRepo.getResponseStatus();
    }

    public MutableLiveData<List<DevAssets>> getAllassests() {
        return mainRepo.getAllDevAssets();
    }

    public void getAllDevAssetsVM(){
        mainRepo.getDevAssets();
    }
}
