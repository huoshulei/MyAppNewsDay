package edu.hsl.myappnewsday.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.bean.NewsBean;
import edu.hsl.myappnewsday.ui.base.BaseAdapter;

/**
 * Created by Administrator on 2016/5/31.
 */
public class NewsAdapter extends BaseAdapter<NewsBean.Data, BaseAdapter.ViewHolder> {
    private static final String TAG              = "NewsAdapter";
    //    final static         int    LOAD_HEADER = 2;//下拉更新
    final static         int    TYPE_FOOTER      = 1;//上拉加载
    final static         int    TYPE_ITEM        = 0;//默认显示item
    //上拉加载
    public static final  int    PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final  int    LOADING_MORE     = 1;
    //上拉加载更多状态-默认为0
    private              int    load_more_status = 0;
//    boolean isFooterEnable = false;

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    @Override
    public BaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new FooterViewHolder(mInflater.inflate(R.layout.layout_footer_updata, parent,
                    false));
        }
        return new ViewHolder(mInflater.inflate(R.layout.layout_news_item, parent, false));
    }

    public interface OnItemClickListener {
        void OnItemClick(View view,int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(final BaseAdapter.ViewHolder holder, final int position) {
        if (holder instanceof NewsAdapter.ViewHolder) {
            ((ViewHolder) holder).tv_summary.setText(data.get(position).getSummary());
            ((ViewHolder) holder).tv_time.setText(data.get(position).getStamp());
//        Log.d(TAG, "onBindViewHolder: " + data.get(position).getTitle());
            ((ViewHolder) holder).tv_title.setText(data.get(position).getTitle());
            ((ViewHolder) holder).itemView.setTag(R.mipmap.a4);
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos=holder.getLayoutPosition();
                        mOnItemClickListener.OnItemClick(v,pos);
                    }
                });
            }
        }
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    footerViewHolder.tv_load.setText("上拉加载更多...");
                    footerViewHolder.pb_load.setVisibility(View.INVISIBLE);
                    break;
                case LOADING_MORE:
                    footerViewHolder.tv_load.setText("正在加载中···");
                    footerViewHolder.pb_load.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    public void changeMoreStatus(int status) {
        load_more_status = status;
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
        ProgressBar pb_load;
        TextView    tv_load;

        public FooterViewHolder(View itemView) {
            super(itemView);
            pb_load = (ProgressBar) itemView.findViewById(R.id.pb_load);
            tv_load = (TextView) itemView.findViewById(R.id.tv_load_bottom);
//            itemView.setVisibility(View.INVISIBLE);//设置默认不显示
        }
    }

//    class HeaderViewHolder extends BaseAdapter.ViewHolder {
//
//        public HeaderViewHolder(View itemView) {
//            super(itemView);
//        }
//    }
}
