package com.appstreet.airtelassignment.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.appstreet.airtelassignment.R;
import com.appstreet.airtelassignment.data.datamodel.DevAssets;
import com.appstreet.airtelassignment.recyclerviewAdapter.DevAssetsAdapter;
import com.appstreet.airtelassignment.utils.ResponseStatus;
import com.appstreet.airtelassignment.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private DevAssetsAdapter devAssetsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        devAssetsObserver();
        responseStatusObserver();
        viewModel.getAllDevAssetsVM();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        devAssetsAdapter = new DevAssetsAdapter(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(devAssetsAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getAllDevAssetsVM();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    private void responseStatusObserver() {
        viewModel.responseStatusLiveData.observe(this, new Observer<ResponseStatus>() {
            @Override
            public void onChanged(@Nullable ResponseStatus responseStatus) {
                if (responseStatus != null) {
                    switch (responseStatus) {
                        case SUCCESS:
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            break;
                        case ERROR:
                            Toast.makeText(MainActivity.this, "Error. Please try again later", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void devAssetsObserver(){
        viewModel.devAssetsLiveData.observe(this, new Observer<List<DevAssets>>() {
            @Override
            public void onChanged(@Nullable List<DevAssets> devAssets) {
                if (devAssets != null) {
                    devAssetsAdapter.updateList(devAssets);
                }
            }
        });
    }
}
