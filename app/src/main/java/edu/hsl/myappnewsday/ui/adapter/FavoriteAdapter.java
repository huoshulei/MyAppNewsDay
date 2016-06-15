package edu.hsl.myappnewsday.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.bean.NewsBean;
import edu.hsl.myappnewsday.common.util.SerializableUtil;

/**
 * Created by Administrator on 2016/6/15.
 */
public class FavoriteAdapter extends BaseQuickAdapter<NewsBean.Data> {

    public FavoriteAdapter(Context context) {
        super(context, R.layout.layout_favortie_itme, null);
    }

//    @Override
//    public void addData(List<NewsBean.Data> data) {
//        super.addData(data);
//    }
//
//
//    public void addData(NewsBean.Data data) {
//        mData.add(data);
//        this.notifyDataSetChanged();
//    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsBean.Data data) {
        baseViewHolder.setText(R.id.tv_title, data.getTitle())
                .setText(R.id.tv_summary, data.getSummary())
                .setText(R.id.tv_time, data.getStamp())
                .setImageBitmap(R.id.iv_icon, SerializableUtil.getBitmap(data.getBitmap()));
    }
}
