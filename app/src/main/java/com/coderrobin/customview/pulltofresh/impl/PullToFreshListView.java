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

package com.coderrobin.customview.pulltofresh.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.coderrobin.customview.pulltofresh.base.IContentView;


public class PullToFreshListView extends ListView implements IContentView {
    public PullToFreshListView(Context context) {
        super(context);
    }

    public PullToFreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToFreshListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isTop() {
        return getFirstVisiblePosition() == 0
                && getChildAt(0).getTop() >= getChildAt(0).getPaddingTop();
    }

    @Override
    public boolean isBottom() {
        // Log.d(VIEW_LOG_TAG, "### last position = " +

        return getAdapter() != null &&
                getLastVisiblePosition() ==
                        getAdapter().getCount() - 1;
    }



}
