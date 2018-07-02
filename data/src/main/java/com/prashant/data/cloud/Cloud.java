package com.prashant.data.cloud;


import com.prashant.restapi.model.User;

import java.util.List;

import rx.Single;

public interface Cloud {

    Single<List<User>> getUserList();

}