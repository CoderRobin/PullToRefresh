/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 coderrobin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.coderrobin.customview.pullToRefresh.base;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


class PullToRefreshLayout extends LinearLayout {
		private View mHeader;
    	private View mContentView;
    	private View mFooter;
		private float mLastDown;
	    private int mHeaderHeight=0;
	    private boolean mDispatchTargetTouchDown=false;
		private PullListener mPullListener;
        public PullToRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
		}

		public PullToRefreshLayout(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public PullToRefreshLayout(Context context) {
			super(context);
        }

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initViews();
	}

	public void setPullListener(PullListener pullListener){
		mPullListener=pullListener;
	}

	private void initViews(){
			int childCount=getChildCount();
			if(childCount>=2) {
				mHeader = getChildAt(0);
				mContentView =getChildAt(1);
			}
			if(childCount==3) {
				mFooter = getChildAt(2);
			}
			resetHeaderMargin();
		}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int resultWidth = 0;
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		if (modeWidth == MeasureSpec.EXACTLY) {
			resultWidth = sizeWidth;
		}
		else {
			resultWidth =mContentView.getMeasuredWidth();
			if (modeWidth == MeasureSpec.AT_MOST) {
				resultWidth = Math.min(resultWidth, sizeWidth);
			}
		}

		int resultHeight = 0;
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

		if (modeHeight == MeasureSpec.EXACTLY) {
			resultHeight = sizeHeight;
		} else {
			resultHeight = mContentView.getMeasuredHeight();
			if (modeHeight == MeasureSpec.AT_MOST) {
				resultHeight = Math.min(resultHeight, sizeHeight);
			}
		}

		setMeasuredDimension(resultWidth, resultHeight);
	}


	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int action=ev.getAction();
		switch (action){
			case MotionEvent.ACTION_DOWN:
				mLastDown=ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				return true;
		}
		return false;

	}

	private boolean isChildOnTop(){
				IContentView contentView=(IContentView)mContentView;
				return contentView.isTop();
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action=event.getAction();
		switch (action){
			case MotionEvent.ACTION_DOWN:
				mDispatchTargetTouchDown=false;
				return true;
			case MotionEvent.ACTION_MOVE:
				float distance=event.getY()-mLastDown;
				if(distance>0&&isChildOnTop()){
					updateHeaderPosition(distance);
					return true;
				}
				else {
					dispatchTouchEventToContentView(event);
				}
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				resetHeaderMargin();
				mDispatchTargetTouchDown=false;
				break;
		}
		return super.onTouchEvent(event);
	}


	private void dispatchTouchEventToContentView(MotionEvent event){
		if (mDispatchTargetTouchDown) {
			mContentView.dispatchTouchEvent(event);
		} else {
			MotionEvent obtain = MotionEvent.obtain(event);
			obtain.setAction(MotionEvent.ACTION_DOWN);
			mDispatchTargetTouchDown = true;
			mContentView.dispatchTouchEvent(obtain);
		}
		mContentView.dispatchTouchEvent(event);
	}

	private void updateHeaderPosition(float distance){
		if(mHeaderHeight==0){
			mHeaderHeight=mHeader.getHeight();
		}
		if(distance>mHeaderHeight){
			distance=mHeaderHeight;
		}
		int margin=(int) distance - mHeaderHeight;
		int progress=(int)(distance*100/mHeaderHeight);
		if(mPullListener!=null){
			mPullListener.onPullProgress(progress);
		}
		setHeaderMargin((int) distance - mHeaderHeight);
	}

	private void setHeaderMargin(int margin){
		LayoutParams layoutParams=(LayoutParams)mHeader.getLayoutParams();
		layoutParams.setMargins(0, margin, 0, 0);
		mHeader.setLayoutParams(layoutParams);
	}

	private void resetHeaderMargin(){
		if(mHeaderHeight==0){
			measureView(mHeader);
			mHeaderHeight=mHeader.getMeasuredHeight();
		}
		setHeaderMargin((int) -mHeaderHeight);	}

	private void measureView(View child) {
		ViewGroup.LayoutParams lp = child.getLayoutParams();
		if(lp == null){
			lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int childMeasureWidth = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
		int childMeasureHeight;
		if(lp.height > 0){
			childMeasureHeight = MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.EXACTLY);
		} else {
			childMeasureHeight = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);//未指定
		}
		child.measure(childMeasureWidth, childMeasureHeight);
	}
}
