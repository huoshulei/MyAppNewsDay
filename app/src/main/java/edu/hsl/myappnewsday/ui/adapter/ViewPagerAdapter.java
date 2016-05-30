package edu.hsl.myappnewsday.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class ViewPagerAdapter extends PagerAdapter {
    LayoutInflater mInflater;
    List<View> data       = new ArrayList<>();

    public ViewPagerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void addViewData(View data) {
        this.data.add(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = data.get(position);
        container.removeView(view);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = data.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
