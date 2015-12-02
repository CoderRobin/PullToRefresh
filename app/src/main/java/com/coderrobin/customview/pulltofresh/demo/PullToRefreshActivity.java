package com.coderrobin.customview.pulltofresh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.coderrobin.customview.R;
import com.coderrobin.customview.pulltofresh.base.IHeaderView;
import com.coderrobin.customview.pulltofresh.base.RefreshListener;

public class PullToRefreshActivity extends Activity {
    private Handler mHandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_circle);
        ListView pullToFreshListView = (ListView) findViewById(R.id.pull_to_fresh_listview);
        pullToFreshListView.setAdapter(new DemoAdapter(this));
        final IHeaderView headerView = (IHeaderView) findViewById(R.id.header);
        headerView.setOnRefreshListener(new RefreshListener() {
            @Override
            public void startLoad() {

            }

            @Override
            public void startRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       headerView.onLoadFinish();
                    }
                },2000);
            }
        });
    }

}
