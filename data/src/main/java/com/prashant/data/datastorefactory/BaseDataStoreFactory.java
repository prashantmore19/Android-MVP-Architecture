package com.prashant.data.datastorefactory;

import android.content.Context;

import com.prashant.data.service.RestfulService;
import com.prashant.restapi.service.RestApiService;

public class BaseDataStoreFactory {
    public RestApiService mRestApiService;
    public RestfulService mRestfulService;

    public BaseDataStoreFactory(Context context) {
        mRestfulService = RestfulService.getInstance(context);
        mRestApiService = mRestfulService.getService();
    }
}