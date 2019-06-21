package com.appstreet.airtelassignment.module.di;

import android.app.Application;

import com.appstreet.airtelassignment.App;
import com.appstreet.airtelassignment.viewmodel.MainViewModel;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {DiModule.class,
        AndroidSupportInjectionModule.class})
@Singleton
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(MainViewModel mainViewModel);
    void inject(App app);
}
