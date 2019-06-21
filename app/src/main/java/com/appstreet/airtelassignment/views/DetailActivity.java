package com.appstreet.airtelassignment.views;

import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.appstreet.airtelassignment.R;
import com.appstreet.airtelassignment.data.datamodel.DevAssets;
import com.bumptech.glide.Glide;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private DevAssets devAssets;

    private Intent intent;

    private TextView usernameTV, typeTV, urlTV, nameTV, descTV, cUrlTV;
    private ImageView avatarImgV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Details");
        getSupportActionBar().show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        usernameTV = (TextView) findViewById(R.id.username);
        typeTV = (TextView) findViewById(R.id.type);
        urlTV = (TextView) findViewById(R.id.url);
        nameTV = (TextView) findViewById(R.id.name);
        descTV = (TextView) findViewById(R.id.description);
        cUrlTV = (TextView) findViewById(R.id.c_url);
        avatarImgV = (ImageView) findViewById(R.id.avatar);

        devAssets = getIntent().getExtras().getParcelable("devdata");
        setData();

    }

    private void setData() {
        if (devAssets!=null){
            usernameTV.setText(devAssets.getUsername());
            typeTV.setText(devAssets.getType());
            urlTV.setText(devAssets.getUrl());
            urlTV.setPaintFlags(urlTV.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            nameTV.setText(devAssets.getRepo().getName());
            descTV.setText(devAssets.getRepo().getDescription());
            cUrlTV.setText(devAssets.getRepo().getUrl());
            cUrlTV.setPaintFlags(cUrlTV.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            Glide.with(this).load(devAssets.getAvatar()).into(avatarImgV);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.url:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(devAssets.getUrl()));
                startActivity(i);
                return;

            case R.id.c_url:
                Intent i2 = new Intent(Intent.ACTION_VIEW);
                i2.setData(Uri.parse(devAssets.getRepo().getUrl()));
                startActivity(i2);
                return;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
