package com.appstreet.airtelassignment.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.support.test.runner.AndroidJUnit4;

import com.appstreet.airtelassignment.data.datamodel.DevAssets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

public class MainViewModelTest {

    @Test
    public void getAllassests_Success_Test() {
        MainViewModel viewModel = new MainViewModel();
        viewModel.getAllDevAssetsVM();
        MutableLiveData<List<DevAssets>> mutableLiveData = viewModel.getAllassests();
        assertTrue(mutableLiveData.getValue().size() > 0);

    }

    @Test
    public void getAllassests_Fail_Test() {
        MainViewModel viewModel = new MainViewModel();
        viewModel.getAllDevAssetsVM();
        MutableLiveData<List<DevAssets>> mutableLiveData = viewModel.getAllassests();
        assertEquals(0, mutableLiveData.getValue().size());

    }

}