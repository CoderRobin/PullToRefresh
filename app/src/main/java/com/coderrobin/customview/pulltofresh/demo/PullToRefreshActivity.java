package com.coderrobin.customview.pulltofresh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.coderrobin.customview.R;

public class PullToRefreshActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        ListView pullToFreshListView = (ListView) findViewById(R.id.pull_to_fresh_listview);
        pullToFreshListView.setAdapter(new DemoAdapter(this));
    }

}
