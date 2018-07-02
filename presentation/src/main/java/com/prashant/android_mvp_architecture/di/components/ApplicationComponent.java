package com.prashant.android_mvp_architecture.di.components;

import android.content.Context;

import com.prashant.android_mvp_architecture.MyApplication;
import com.prashant.android_mvp_architecture.di.modules.ApplicationModule;
import com.prashant.android_mvp_architecture.feature.home.activity.MainActivity;
import com.prashant.android_mvp_architecture.view.BaseActivity;
import com.prashant.domain.executor.PostExecutionThread;
import com.prashant.domain.executor.ThreadExecutor;
import com.prashant.domain.feature.user.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MyApplication myApplication);

    //void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    UserRepository userRepository();

    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

}