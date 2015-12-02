package com.coderrobin.customview.pulltofresh.base;

/**
 * Created by renzhibin on 15/11/30.
 */
public interface IHeaderView {
    void show();
    void hide();
    void onDrag(int distance);
    void onFinishDrag();
    void onLoadFinish();
    void setOnRefreshListener(RefreshListener pRefreshListener);
}
