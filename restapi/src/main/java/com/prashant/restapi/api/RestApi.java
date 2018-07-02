package com.prashant.restapi.api;

import com.prashant.restapi.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

@SuppressWarnings("SpellCheckingInspection")
public interface RestApi {

    @GET("/posts")
    Call<List<User>> getUserList();

}