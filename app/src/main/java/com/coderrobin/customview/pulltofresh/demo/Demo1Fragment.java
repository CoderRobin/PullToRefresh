package com.coderrobin.customview.pulltofresh.demo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.coderrobin.customview.R;
import com.coderrobin.customview.pulltofresh.base.IHeaderView;
import com.coderrobin.customview.pulltofresh.base.RefreshListener;


public class Demo1Fragment extends Fragment {
    private Handler mHandler=new Handler();

    public Demo1Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.demo1_fragment, container, false);
        init(view);
        return view;

    }

    private void init(View rootView){
        ListView pullToFreshListView = (ListView) rootView.findViewById(R.id.pull_to_fresh_listview);
        pullToFreshListView.setAdapter(new DemoAdapter(getActivity()));
        final IHeaderView headerView = (IHeaderView) rootView.findViewById(R.id.header);
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
