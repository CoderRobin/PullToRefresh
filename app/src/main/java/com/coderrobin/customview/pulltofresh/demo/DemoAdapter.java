package com.coderrobin.customview.pulltofresh.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coderrobin.customview.R;

/**
 * Created by renzhibin on 15/11/29.
 */
public class DemoAdapter extends BaseAdapter {
    private Context mContext;

    public DemoAdapter(Context pContext) {
        mContext = pContext;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.demo_item, null);
            textView = (TextView) view.findViewById(R.id.textview);
            ViewHolder viewHolder = new ViewHolder(textView);
            view.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.textview.setText(i + "");
        return view;
    }

    private static class ViewHolder {
        TextView textview;

        public ViewHolder(TextView textview) {
            this.textview = textview;
        }
    }
}
