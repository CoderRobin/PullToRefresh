package com.coderrobin.customview.pulltofresh.base;

/**
 * Created by renzhibin on 15/11/30.
 */
public interface IHeaderView {
    void show();
    void hide();
    int getDragTotalHeight();
    void onProgress(int progress);
    void onFinishDrag();
    void onLoadFinish();
    void setOnRefreshListener(RefreshListener pRefreshListener);
}
