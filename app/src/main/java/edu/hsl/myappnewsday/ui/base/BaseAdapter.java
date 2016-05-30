package edu.hsl.myappnewsday.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public abstract class BaseAdapter<T, VH extends BaseAdapter.ViewHolder> extends RecyclerView
        .Adapter<VH> {
    List<T> data = new ArrayList<>();
    LayoutInflater mInflater;

    public BaseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void add(T data) {
        this.data.add(data);
    }

    public void add(List<T> data) {
        this.data.addAll(data);
    }

    public void addTop(T data) {
        this.data.add(0, data);
    }

    public void addTop(List<T> data) {
        this.data.addAll(0, data);
    }

    public void clear() {
        data.clear();
    }

    public List<T> getData() {
        return data;
    }

    public void upData() {
        this.notifyDataSetChanged();
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(VH holder, int position);

    @Override
    public int getItemCount() {
        return data.size();
    }

   public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
