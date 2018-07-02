package com.prashant.android_mvp_architecture.feature.home.presenter;

import android.support.annotation.NonNull;

import com.prashant.android_mvp_architecture.execption.ErrorMessageFactory;
import com.prashant.android_mvp_architecture.feature.home.view.HomeView;
import com.prashant.domain.exception.DefaultErrorBundle;
import com.prashant.domain.exception.ErrorBundle;
import com.prashant.domain.feature.user.interactor.GetUserList;
import com.prashant.domain.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.SingleSubscriber;

@Singleton
public class HomePresenter {

    private HomeView mNewView;
    private final GetUserList mGetUserListUseCase;

    @Inject
    public HomePresenter(GetUserList getTripUseCase) {
        mGetUserListUseCase = getTripUseCase;
    }

    public void setView(@NonNull HomeView view) {
        mNewView = view;
    }

    public void showLoadingView() {
        if (mNewView != null) {
            mNewView.showLoading();
        }
    }

    public void hideLoadingView() {
        if (mNewView != null) {
            mNewView.hideLoading();
        }
    }

    private String getErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = null;
        if (mNewView != null && mNewView.getViewContext() != null) {
            errorMessage = ErrorMessageFactory.create(mNewView.getViewContext(), errorBundle.getException());
        }
        return errorMessage;
    }

    public void getUserListRequest() {
        showLoadingView();
        mGetUserListUseCase.execute(new GetUserListSubscriber());
    }

    private final class GetUserListSubscriber extends SingleSubscriber<List<User>> {

        @Override
        public void onSuccess(List<User> userList) {
            hideLoadingView();
            if (mNewView != null) {
                mNewView.onGetUserListApiSuccess(userList);
            }
        }

        @Override
        public void onError(Throwable e) {
            hideLoadingView();
            String errorMessage = getErrorMessage(new DefaultErrorBundle((Exception) e));
            if (mNewView != null) {
                mNewView.onGetUserListApiError(errorMessage);
            }
        }

    }

}