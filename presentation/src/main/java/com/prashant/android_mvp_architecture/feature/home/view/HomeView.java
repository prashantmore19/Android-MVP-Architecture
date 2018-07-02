package com.prashant.android_mvp_architecture.feature.home.view;


import com.prashant.android_mvp_architecture.view.DataView;
import com.prashant.domain.model.User;

import java.util.List;

public interface HomeView extends DataView {

    void onGetUserListApiSuccess(List<User> userCollection);

    void onGetUserListApiError(String errorMessage);

}