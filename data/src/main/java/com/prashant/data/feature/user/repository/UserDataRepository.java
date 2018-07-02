package com.prashant.data.feature.user.repository;

import com.prashant.data.feature.user.repository.datasource.UserDataStore;
import com.prashant.data.feature.user.repository.datasource.UserDataStoreFactory;
import com.prashant.domain.feature.user.repository.UserRepository;
import com.prashant.domain.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Single;

/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements UserRepository {

    private final UserDataStoreFactory mUserDataStoreFactory;

    @Inject
    public UserDataRepository(UserDataStoreFactory userDataStoreFactory) {
        mUserDataStoreFactory = userDataStoreFactory;
    }

    @Override
    public Single<List<User>> getUserList() {
        final UserDataStore userDataStore;
        userDataStore = mUserDataStoreFactory.create();
        return userDataStore.getUserList();
    }

}