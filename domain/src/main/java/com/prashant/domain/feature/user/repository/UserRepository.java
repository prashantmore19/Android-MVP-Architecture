package com.prashant.domain.feature.user.repository;

import com.prashant.domain.model.User;

import java.util.List;

import rx.Single;

public interface UserRepository {

    Single<List<User>> getUserList();

}