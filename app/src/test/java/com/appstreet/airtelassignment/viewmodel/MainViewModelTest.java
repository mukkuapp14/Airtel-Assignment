package com.appstreet.airtelassignment.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.appstreet.airtelassignment.data.datamodel.DevAssets;
import com.appstreet.airtelassignment.repo.MainRepo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.*;

public class MainViewModelTest {

    @Before
    public void setUp() throws Exception {
    }

    MockWebServer mockWebServer = new MockWebServer();

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