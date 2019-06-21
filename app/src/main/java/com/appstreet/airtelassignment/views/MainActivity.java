package com.appstreet.airtelassignment.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.appstreet.airtelassignment.R;
import com.appstreet.airtelassignment.data.datamodel.DevAssets;
import com.appstreet.airtelassignment.recyclerviewAdapter.DevAssetsAdapter;
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

    private void devAssetsObserver(){
        viewModel.devAssetsLiveData.observe(this, new Observer<List<DevAssets>>() {
            @Override
            public void onChanged(@Nullable List<DevAssets> devAssets) {
                if (devAssets != null) {
                    devAssetsAdapter.updateList(devAssets);
                }else {
                    Log.d("MUKESH_CHECK", "YES");
                    Toast.makeText(MainActivity.this, "Check your internet connection.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}