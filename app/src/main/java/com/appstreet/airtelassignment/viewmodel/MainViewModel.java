package com.appstreet.airtelassignment.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.appstreet.airtelassignment.App;
import com.appstreet.airtelassignment.data.datamodel.DevAssets;
import com.appstreet.airtelassignment.repo.MainRepo;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {
    public LiveData<List<DevAssets>> devAssetsLiveData;

    @Inject
    public MainRepo mainRepo;

    public MainViewModel() {
        App.getAppComponent().inject(this);
        this.devAssetsLiveData = getAllassests();
    }

    public MutableLiveData<List<DevAssets>> getAllassests() {
        return mainRepo.getAllDevAssets();
    }

    public void getAllDevAssetsVM(){
        mainRepo.getDevAssets();
    }
}
