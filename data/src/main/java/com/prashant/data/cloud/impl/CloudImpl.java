package com.prashant.data.cloud.impl;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.prashant.data.cloud.Cloud;
import com.prashant.data.service.RestfulService;
import com.prashant.data.util.DataUtility;
import com.prashant.restapi.model.User;
import com.prashant.restapi.service.RestApiService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Single;
import rx.SingleSubscriber;
import timber.log.Timber;

@Singleton
public class CloudImpl implements Cloud {

    private RestApiService mRestApiService = null;
    private RestfulService mRestfulService = null;
    private Context mContext;

    @Inject
    public CloudImpl(Context context) {
        mContext = context;
        mRestApiService = RestApiService.getInstance();
        mRestfulService = RestfulService.getInstance(context);
        mRestApiService = mRestfulService.getService();
    }

    @Override
    public Single<List<User>> getUserList() {
        return Single.create(new Single.OnSubscribe<List<User>>() {
            @Override
            public void call(final SingleSubscriber<? super List<User>> subscriber) {
                try {
                    mRestApiService.getUserList(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            if (response.isSuccessful()) {
                                List<User> userList = response.body();
                                Timber.e("Get User List API Success ==>> " + new GsonBuilder().setPrettyPrinting().create().toJson(userList));
                                subscriber.onSuccess(userList);
                            } else {
                                String errorMessage = DataUtility.instance().generateRestAPIError(response.code(), response.message());
                                Timber.e("Get User List API Failure ==>> " + errorMessage);
                                subscriber.onError(DataUtility.instance().generateRestAPIException(response.code(), errorMessage));
                            }
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Timber.e("Get User List API Failure ==>> " + t.getMessage());
                            subscriber.onError(t);
                        }
                    });
                } catch (Exception e) {
                    Timber.e(e.getMessage());
                    subscriber.onError(e);
                }
            }
        });
    }

}