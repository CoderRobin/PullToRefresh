package com.coderrobin.customview.pulltofresh.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.coderrobin.customview.pulltofresh.base.IHeaderView;

/**
 * Created by renzhibin on 15/11/30.
 */
public class PullToFreshHeader extends LinearLayout implements IHeaderView {
    public PullToFreshHeader(Context context) {
        super(context);
    }

    public PullToFreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToFreshHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public int getDragTotalHeight() {
        return getHeight();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
        resetHeaderMargin();
    }

    @Override
    public void onProgress(int progress) {
        setHeaderMargin((progress-100)*getDragTotalHeight()/100);
    }

    private void setHeaderMargin(int margin) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.setMargins(0, margin, 0, 0);
        setLayoutParams(layoutParams);
    }

    private void resetHeaderMargin() {
        setHeaderMargin((int) -getMeasuredHeight());
    }
}
