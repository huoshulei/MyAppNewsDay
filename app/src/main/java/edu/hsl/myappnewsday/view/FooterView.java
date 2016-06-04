package edu.hsl.myappnewsday.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreFooterLayout;

import edu.hsl.myappnewsday.R;

/**
 * Created by Administrator on 2016/6/3.
 * 废弃
 */
public class FooterView extends SwipeLoadMoreFooterLayout {
    TextView    tv_text;
    ProgressBar pb_drop;
    int         footerHeight;

    public FooterView(Context context) {
        super(context);
    }

    public FooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        footerHeight = getResources().getDimensionPixelOffset(R.dimen.load_more_footer_height);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_text = (TextView) findViewById(R.id.tv_load_bottom);
        pb_drop = (ProgressBar) findViewById(R.id.pb_load);
    }

    /**
     * 刷新是调用
     */
    @Override
    public void onLoadMore() {
        super.onLoadMore();
        tv_text.setText("加载中...");
        pb_drop.setVisibility(VISIBLE);
    }

    /**
     * 开始是调用
     */
    @Override
    public void onPrepare() {
        super.onPrepare();
    }

    /**
     * 拉动时调用
     */
    @Override
    public void onSwipe(int y, boolean isComplete) {
        super.onSwipe(y, isComplete);
        if (isComplete) {
            pb_drop.setVisibility(INVISIBLE);
            if (Math.abs(y) >= footerHeight) {
                tv_text.setText("松开刷新");
            } else {
                tv_text.setText("上拉刷新");
            }
        }
    }

    @Override
    public void onRelease() {
        super.onRelease();
    }

    /**
     * 刷新完成时调用
     */
    @Override
    public void complete() {
        super.complete();
        pb_drop.setVisibility(INVISIBLE);
    }

    /**
     * 在complete之后调用
     */
    @Override
    public void onReset() {
        super.onReset();
    }
}
