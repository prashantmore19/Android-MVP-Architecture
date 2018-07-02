package com.prashant.domain.feature.user.interactor;

import com.prashant.domain.executor.PostExecutionThread;
import com.prashant.domain.executor.ThreadExecutor;
import com.prashant.domain.feature.user.repository.UserRepository;

import javax.inject.Inject;

import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class GetUserList {

    private final UserRepository mUserRepository;
    private final ThreadExecutor mThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;
    private Subscription mSubscription = Subscriptions.empty();

    @Inject
    public GetUserList(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.mUserRepository = userRepository;
        mThreadExecutor = threadExecutor;
        mPostExecutionThread = postExecutionThread;
    }

    @SuppressWarnings("unchecked")
    public void execute(SingleSubscriber UseCaseSubscriber) {
        mSubscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler())
                .subscribe(UseCaseSubscriber);
    }

    public Single buildUseCaseObservable() {
        return this.mUserRepository.getUserList();
    }

    public void unsubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

}