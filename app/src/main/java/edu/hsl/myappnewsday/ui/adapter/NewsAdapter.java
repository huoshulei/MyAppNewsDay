package edu.hsl.myappnewsday.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.bean.NewsBean;
import edu.hsl.myappnewsday.ui.base.BaseAdapter;

/**
 * Created by Administrator on 2016/5/31.
 */
public class NewsAdapter extends BaseAdapter<NewsBean.Data, NewsAdapter.ViewHolder> {
    private static final String TAG         = "NewsAdapter";
    final static         int    LOAD_HEADER = 1;//下拉更新
    final static         int    LOAD_FOOTER = 2;//上拉加载
    final static         int    LOAD_ITEM   = 0;//默认显示item
    boolean isFooterEnable = false;

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(mInflater.inflate(R.layout.layout_news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_summary.setText(data.get(position).getSummary());
        holder.tv_time.setText(data.get(position).getStamp());
//        Log.d(TAG, "onBindViewHolder: " + data.get(position).getTitle());
        holder.tv_title.setText(data.get(position).getTitle());
    }

    class ViewHolder extends BaseAdapter.ViewHolder {
        ImageView iv_icon;
        TextView  tv_title;
        TextView  tv_time;
        TextView  tv_summary;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_summary = (TextView) itemView.findViewById(R.id.tv_summary);
        }
    }

    class FooterViewHolder extends BaseAdapter.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    class HeaderViewHolder extends BaseAdapter.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
