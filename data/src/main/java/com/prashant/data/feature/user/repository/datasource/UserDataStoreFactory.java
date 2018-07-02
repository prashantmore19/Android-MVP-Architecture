package com.prashant.data.feature.user.repository.datasource;

import android.content.Context;

import com.prashant.data.cloud.Cloud;
import com.prashant.data.datastorefactory.BaseDataStoreFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserDataStoreFactory extends BaseDataStoreFactory {

    private CloudUserDataStore mCloudUserDataStore;
    private Cloud mCloud;

    @Inject
    public UserDataStoreFactory(Context context, Cloud cloud) {
        super(context);
        mCloud = cloud;
    }

    public UserDataStore create() {
        return createCloudDataStore();
    }

    /**
     * Create {@link UserDataStore} to retrieve data from the Cloud.
     */
    public UserDataStore createCloudDataStore() {
        if (mCloudUserDataStore == null) {
            mCloudUserDataStore = new CloudUserDataStore(mCloud);
        }
        return mCloudUserDataStore;
    }

}