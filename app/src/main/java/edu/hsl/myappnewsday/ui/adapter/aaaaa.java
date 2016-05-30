package edu.hsl.myappnewsday.ui.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import edu.hsl.myappnewsday.ui.base.BaseAdapter;

/**
 * Created by Administrator on 2016/5/30.
 */
public class aaaaa extends BaseAdapter<String, aaaaa.ViewHolder> {


    public aaaaa(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    class ViewHolder extends BaseAdapter.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
