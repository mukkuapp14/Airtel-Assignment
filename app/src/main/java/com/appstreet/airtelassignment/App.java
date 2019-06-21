package com.appstreet.airtelassignment;

import android.app.Activity;
import android.app.Application;

import com.appstreet.airtelassignment.module.di.AppComponent;
import com.appstreet.airtelassignment.module.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {

    private static AppComponent component;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .application(this)
                .build();
        component.inject(this);
    }

    public static AppComponent getAppComponent() {
        return component;
    }
}
