package com.prashant.android_mvp_architecture.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.prashant.android_mvp_architecture.MyApplication;
import com.prashant.android_mvp_architecture.R;
import com.prashant.android_mvp_architecture.di.components.ApplicationComponent;

import butterknife.BindView;
import timber.log.Timber;

public abstract class BaseActivity extends AppCompatActivity implements DataView {

    @BindView(R.id.progress_bar_layout)
    RelativeLayout mProgressBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    @Override
    public void showLoading() {
        try {
            mProgressBarLayout.setVisibility(View.VISIBLE);
            setProgressBarIndeterminateVisibility(true);
        } catch (Exception e) {
            Timber.e("Exception when trying to show loading ==>> " + e.getMessage());
        }
    }

    @Override
    public void hideLoading() {
        try {
            mProgressBarLayout.setVisibility(View.GONE);
            setProgressBarIndeterminateVisibility(false);
        } catch (Exception e) {
            Timber.e("Exception when trying to hide loading ==>> " + e.getMessage());
        }
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

}