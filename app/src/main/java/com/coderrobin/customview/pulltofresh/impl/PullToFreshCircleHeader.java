package com.coderrobin.customview.pulltofresh.impl;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.coderrobin.customview.R;
import com.coderrobin.customview.pulltofresh.base.IHeaderView;
import com.coderrobin.customview.pulltofresh.base.RefreshListener;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by renzhibin on 15/11/30.
 */
public class PullToFreshCircleHeader extends LinearLayout implements IHeaderView {
    private static final int REFRESH_PROGRESS=60;
    private static final int ANIMATION_TIME=200;
    private RefreshListener mRefreshListener;
    private int mProgress;
    private ProgressBar mProgressBar;
    public PullToFreshCircleHeader(Context context) {
        super(context);
        init();
    }

    public PullToFreshCircleHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullToFreshCircleHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        View.inflate(getContext(), R.layout.pull_header, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initViews();
    }

    private void initViews(){
        mProgressBar=(ProgressBar)findViewById(R.id.progressbar);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public int getDragTotalHeight() {
        return 500;
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
    }



    @Override
    public void onDrag(int distance) {
        if(distance>getDragTotalHeight()){
            distance=getDragTotalHeight();
        }
        mProgress = (int) (distance * 100 / getDragTotalHeight());
        setPosition(distance);
    }

    private void setPosition(int distance) {
        Log.v("setPosition", distance+"");
        ViewHelper.setTranslationY(this, distance);
       // ViewHelper.setTranslationY(mProgressBar, distance);
    }



    public void setOnRefreshListener(RefreshListener pRefreshListener){
        mRefreshListener=pRefreshListener;
    }



    @Override
    public void onLoadFinish() {
        startReturnAnimation();
    }

    private void startReturnAnimation(){
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(this, "translationY", getTranslationY(), 0);
        objectAnimator.setDuration(ANIMATION_TIME);
        objectAnimator.start();
    }

    private void startLoadAnimation(){
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(this, "translationY", getTranslationY(), getDragTotalHeight());
        objectAnimator.setDuration(ANIMATION_TIME);
        objectAnimator.start();
    }



}
