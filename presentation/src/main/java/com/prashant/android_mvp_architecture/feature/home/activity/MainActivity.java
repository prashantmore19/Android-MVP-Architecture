package com.prashant.android_mvp_architecture.feature.home.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.prashant.android_mvp_architecture.R;
import com.prashant.android_mvp_architecture.di.HasComponent;
import com.prashant.android_mvp_architecture.di.components.ApplicationComponent;
import com.prashant.android_mvp_architecture.di.components.DaggerApplicationComponent;
import com.prashant.android_mvp_architecture.di.modules.ApplicationModule;
import com.prashant.android_mvp_architecture.feature.home.presenter.HomePresenter;
import com.prashant.android_mvp_architecture.feature.home.view.HomeView;
import com.prashant.android_mvp_architecture.view.BaseActivity;
import com.prashant.domain.model.User;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements HasComponent<ApplicationComponent>, HomeView {

    @Inject
    HomePresenter mHomePresenter;

    @BindView(R.id.user_list_text_view)
    AppCompatTextView mUserListTextView;

    @BindString(R.string.message_no_user_found)
    String mNoUserFoundMessage;

    @BindString(R.string.message_get_user_list_api_successful)
    String mGetUserListAPISuccessfulMessage;

    @BindString(R.string.message_get_user_list_api_failure)
    String mGetUserListAPIFailureMessage;

    private ApplicationComponent mApplicationComponent;
    private Context mContext;
    Unbinder mButterKnifeUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ButterKnife.bind(this);
        initializeActivityComponents();
    }

    private void initializeActivityComponents() {
        initializeInjector();
        getApplicationComponent().inject(this);
        if (mHomePresenter != null) {
            mHomePresenter.setView(this);
        }
    }

    private void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    @OnClick(R.id.user_list_button)
    public void onClickGetUserListButton() {
        Timber.e("onClickGetUserListButton");
        getUserList();
    }

    @Override
    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    private void getUserList() {
        if (mHomePresenter != null) {
            mHomePresenter.getUserListRequest();
        }
    }

    @Override
    public void onGetUserListApiSuccess(List<User> userCollection) {
        try {
            Timber.d(mGetUserListAPISuccessfulMessage);
            Toast.makeText(mContext, mGetUserListAPISuccessfulMessage, Toast.LENGTH_LONG).show();
            if (userCollection != null && userCollection.size() > 0) {
                mUserListTextView.setText(new GsonBuilder().setPrettyPrinting().create().toJson(userCollection));
            } else {
                mUserListTextView.setText(mNoUserFoundMessage);
            }
        } catch (Exception e) {
            Timber.e("Exception caught in onGetUserListApiSuccess ==>> %s ", e.getMessage());
        }
    }

    @Override
    public void onGetUserListApiError(String errorMessage) {
        try {
            Timber.d(mGetUserListAPIFailureMessage);
            Toast.makeText(mContext, mGetUserListAPIFailureMessage, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Timber.e("Exception caught in onGetUserListApiError ==>> %s ", e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        if (mButterKnifeUnBinder != null) {
            mButterKnifeUnBinder.unbind();
        }
        super.onDestroy();
    }
}