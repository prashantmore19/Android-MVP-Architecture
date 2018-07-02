package com.prashant.data.feature.user.repository.datasource;

import com.prashant.data.cloud.Cloud;
import com.prashant.data.mapper.UserDataMapper;
import com.prashant.domain.model.User;

import java.util.List;

import rx.Single;
import rx.functions.Func1;

/**
 * {@link UserDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudUserDataStore implements UserDataStore {

    private final Cloud mCloud;

    public CloudUserDataStore(Cloud cloud) {
        mCloud = cloud;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Single<List<User>> getUserList() {
        return mCloud.getUserList()
                .map(new Func1<List<com.prashant.restapi.model.User>, List<User>>() {
                    @Override
                    public List<User> call(List<com.prashant.restapi.model.User> requestStatusEntities) {
                        return UserDataMapper.transform(requestStatusEntities);
                    }
                });
    }

}