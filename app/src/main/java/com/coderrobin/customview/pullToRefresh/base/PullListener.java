package com.coderrobin.customview.pullToRefresh.base;

/**
 * Created by renzhibin on 15/11/29.
 */
public interface PullListener {
    void onPullStart();

    void onPullEnd();

    void onPullProgress(int progress);
}
