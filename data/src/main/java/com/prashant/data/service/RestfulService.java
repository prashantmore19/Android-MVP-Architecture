package com.prashant.data.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.prashant.data.util.DataUtility;
import com.prashant.domain.constant.DomainConstants;
import com.prashant.restapi.service.RestApiService;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Locale;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import timber.log.Timber;

public class RestfulService {

    private static final String HEADER_CONTENT_TYPE_KEY = "Content-Type";
    private static final String HEADER_ACCEPT_KEY = "Accept";
    private static final String HEADER_CONTENT_TYPE_VALUE = "application/json";
    private static final String HEADER_ACCEPT_VALUE = "application/json";

    public RestApiService mRestApiService;
    private static RestfulService sRestfulService;

    private OkHttpClient mClient;
    private SharedPreferences mSharedPreferences;
    private Interceptor mDefaultInterceptor;

    private static Headers.Builder sHeaderBuilder = new Headers.Builder();
    private Context mContext;


    private RestfulService(Context context) {
        mContext = context;
        initRestfulService();
    }

    public static RestfulService getInstance(Context context) {
        if (sRestfulService == null) {
            synchronized (RestApiService.class) {
                if (sRestfulService == null) {
                    sRestfulService = new RestfulService(context);
                }
            }
        }

        return sRestfulService;
    }

    public void initRestfulService() {

        mRestApiService = RestApiService.getInstance();
        initEndPoint();

        // initialize http client
        initHttpClient();
        mRestApiService.setHttpClient(mClient);

        mRestApiService.initRestAPI();
    }

    public void initHttpClient() {
        // initialize default interceptor
        initDefaultInterceptor();

        mClient = new OkHttpClient().newBuilder().addInterceptor(mDefaultInterceptor).build();
    }

    public void initEndPoint() {
        String apiEndpoint = DomainConstants.API_ENDPOINT;
        Timber.d("API Endpoint ==>>  " + apiEndpoint);
        mRestApiService.setEndpoint(apiEndpoint);
    }

    public RestApiService getService() {
        return mRestApiService;
    }

    public void initDefaultHeaderBuilder() {
        sHeaderBuilder.set(HEADER_CONTENT_TYPE_KEY, HEADER_CONTENT_TYPE_VALUE);
        sHeaderBuilder.set(HEADER_ACCEPT_KEY, HEADER_ACCEPT_VALUE);
    }

    public void initDefaultInterceptor() {
        initDefaultHeaderBuilder();

        mDefaultInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException, UnknownHostException {
                Request request = chain.request();

                // Required Headers.
                if (sHeaderBuilder != null) {
                    Request.Builder requestBuilder = request.newBuilder();
                    requestBuilder.headers(sHeaderBuilder.build());
                    request = requestBuilder.build();
                }

                long t1 = System.nanoTime();
                Timber.d(String.format("Sending request %s on %s%n%s",
                        request.url(), chain.connection(), request.headers()));

                Timber.d("METHOD TYPE ==>> " + request.method());

                Headers headers = request.headers();

                Timber.d("REQUEST HEADERS ==>> " + headers.size());
                for (String name : headers.names()) {
                    Timber.d(name + " : " + headers.get(name));
                }


                if (request.body() != null) {
                    Timber.d("BODY: " + DataUtility.requestBodyToString(request.body()));

                }

                Response response = chain.proceed(request);

                long t2 = System.nanoTime();
                Timber.d(String.format(Locale.US, "Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));


                headers = response.headers();

                Timber.d("RESPONSE HEADERS ==>> " + headers.size());
                for (String name : headers.names()) {
                    Timber.d(name + " : " + headers.get(name));
                }

                if (response.body() != null) {
                    ResponseBody jsonObj = response.body();

                    if (jsonObj != null) {
                        Timber.d("BODY: " + jsonObj.toString());
                    }
                }

                return response;
            }
        };
    }

}