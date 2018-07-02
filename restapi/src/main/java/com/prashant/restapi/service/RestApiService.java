package com.prashant.restapi.service;

import com.prashant.restapi.api.RestApi;
import com.prashant.restapi.model.User;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is the Workflow for RestApiService.
 * setApiEndPoint(String apiEndpoint) ==> setHttpClient => addInterceptors ==>  initRestAPI()
 **/
public class RestApiService {

    private String mApiBaseUrl;
    private RestApi mRestApi;
    private OkHttpClient mClient;
    private static RestApiService sRestApiService;

    private RestApiService() {
    }

    public static RestApiService getInstance() {
        if (sRestApiService == null) {
            synchronized (RestApiService.class) {
                if (sRestApiService == null) {
                    sRestApiService = new RestApiService();
                }
            }
        }
        return sRestApiService;
    }

    public void setEndpoint(String apiEndpoint) {
        mApiBaseUrl = apiEndpoint;
    }

    public void setHttpClient(OkHttpClient client) {
        mClient = client;
    }

    public void initRestAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mApiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .build();
        mRestApi = retrofit.create(RestApi.class);
    }

    public void getUserList(Callback<List<User>> callback) {
        mRestApi.getUserList().enqueue(callback);
    }

}