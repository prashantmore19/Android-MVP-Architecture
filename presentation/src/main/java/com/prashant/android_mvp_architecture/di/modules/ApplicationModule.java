package com.prashant.android_mvp_architecture.di.modules;

import android.content.Context;

import com.prashant.android_mvp_architecture.feature.home.activity.MainActivity;
import com.prashant.android_mvp_architecture.MyApplication;
import com.prashant.android_mvp_architecture.UIThread;
import com.prashant.data.cloud.Cloud;
import com.prashant.data.cloud.impl.CloudImpl;
import com.prashant.data.executor.JobExecutor;
import com.prashant.data.feature.user.repository.UserDataRepository;
import com.prashant.domain.executor.PostExecutionThread;
import com.prashant.domain.executor.ThreadExecutor;
import com.prashant.domain.feature.user.interactor.GetUserList;
import com.prashant.domain.feature.user.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private MyApplication mApplication;

    public ApplicationModule(MyApplication application) {
        mApplication = application;
    }

    public ApplicationModule(MainActivity mainActivity) {
    }

    @Singleton
    @Provides
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Singleton
    @Provides
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Singleton
    @Provides
    Context provideApplicationContext() {
        return mApplication;
    }

    @Singleton
    @Provides
    GetUserList provideGetUserListUseCase(UserRepository userRepository,
                                          ThreadExecutor threadExecutor,
                                          PostExecutionThread postExecutionThread) {
        return new GetUserList(userRepository, threadExecutor, postExecutionThread);
    }

    @Singleton
    @Provides
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

    @Singleton
    @Provides
    Cloud provideCloud(CloudImpl cloud) {
        return cloud;
    }

}