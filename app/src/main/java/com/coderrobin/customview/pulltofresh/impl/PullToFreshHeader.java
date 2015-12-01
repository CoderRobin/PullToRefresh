package com.coderrobin.customview.pulltofresh.impl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.coderrobin.customview.R;
import com.coderrobin.customview.pulltofresh.base.IHeaderView;
import com.coderrobin.customview.pulltofresh.base.RefreshListener;

/**
 * Created by renzhibin on 15/11/30.
 */
public class PullToFreshHeader extends LinearLayout implements IHeaderView {
    private static final int REFRESH_PROGRESS=60;
    private static final int ANIMATION_TIME=200;
    private RefreshListener mRefreshListener;
    private int mProgress;
    private ProgressBar mProgressBar;
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
    protected void onFinishInflate() {
        super.onFinishInflate();
        initViews();
    }

    private void initViews(){
        mProgressBar=(ProgressBar)findViewById(R.id.progressbar);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public int getDragTotalHeight() {
        return getHeight();
    }

    @Override
    public void show() {

    }

    @Override
    public void onFinishDrag() {
        if(mProgress>=REFRESH_PROGRESS){
            startLoadAnimation();
            if(mRefreshListener!=null){
                mRefreshListener.startRefresh();
            }
        }
        else {
            startReturnAnimation();
        }
    }

    @Override
    public void hide() {
        resetHeaderMargin();
    }



    @Override
    public void onProgress(int progress) {
        mProgress = progress;
        setMargin((progress - 100) * getDragTotalHeight() / 100);
    }

    private void setMargin(int margin) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.setMargins(0, margin, 0, 0);
        setLayoutParams(layoutParams);
    }

    private int getMargin(){
        return ((LayoutParams) getLayoutParams()).topMargin;
    }

    private void resetHeaderMargin() {
        setMargin((int) -getMeasuredHeight());
    }

    public void setOnRefreshListener(RefreshListener pRefreshListener){
        mRefreshListener=pRefreshListener;
    }



    @Override
    public void onLoadFinish() {
        mProgressBar.setVisibility(View.GONE);
        startReturnAnimation();
    }

    private void startReturnAnimation(){
        ObjectAnimator objectAnimator=ObjectAnimator.ofInt(this,"margin",getMargin(),-getMeasuredHeight());
        objectAnimator.setDuration(ANIMATION_TIME);
        objectAnimator.start();
    }

    private void startLoadAnimation(){
        ObjectAnimator objectAnimator=ObjectAnimator.ofInt(this,"margin",getMargin(),0);
        objectAnimator.setDuration(ANIMATION_TIME);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mProgressBar.setVisibility(View.VISIBLE);
            }
        });
        objectAnimator.start();
    }



}
