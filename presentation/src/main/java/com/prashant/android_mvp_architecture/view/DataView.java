package com.prashant.android_mvp_architecture.view;

import android.content.Context;

public interface DataView {

    void showLoading();

    void hideLoading();

    Context getViewContext();

}