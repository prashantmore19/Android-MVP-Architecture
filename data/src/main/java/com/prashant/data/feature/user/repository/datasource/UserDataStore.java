package com.prashant.data.feature.user.repository.datasource;

import com.prashant.domain.model.User;

import java.util.List;

import rx.Single;

public interface UserDataStore {

    Single<List<User>> getUserList();

}