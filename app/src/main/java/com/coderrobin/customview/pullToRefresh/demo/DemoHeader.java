package com.coderrobin.customview.pullToRefresh.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.coderrobin.customview.pullToRefresh.base.PullListener;

/**
 * Created by renzhibin on 15/11/29.
 */
public class DemoHeader extends LinearLayout implements PullListener {
    public DemoHeader(Context context) {
        super(context);
    }

    public DemoHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DemoHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onPullStart() {

    }

    @Override
    public void onPullEnd() {

    }

    @Override
    public void onPullProgress(int progress) {

    }
}
