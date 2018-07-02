package com.prashant.android_mvp_architecture;

import android.app.Application;

import com.prashant.android_mvp_architecture.di.components.ApplicationComponent;
import com.prashant.android_mvp_architecture.di.components.DaggerApplicationComponent;
import com.prashant.android_mvp_architecture.di.modules.ApplicationModule;

import timber.log.Timber;

public class MyApplication extends Application {

    private static Application sApplication;
    private ApplicationComponent mApplicationComponent;
    private ApplicationModule mApplicationModule;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        initializeInjector();
        getApplicationComponent().inject(this);

        Timber.plant(new Timber.DebugTree());
        System.out.println("Planted Debug Tree!");
    }

    private void initializeInjector() {
        mApplicationModule = new ApplicationModule(this);
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(mApplicationModule)
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static Application getApplication() {
        return sApplication;
    }

}