package edu.hsl.myappnewsday.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.common.util.TimeUtil;

/**
 * Created by Administrator on 2016/6/3.
 * 废弃
 */
public class HeaderView extends SwipeRefreshHeaderLayout {
    ImageView   iv_drop;
    TextView    tv_text;
    TextView    tv_time;
    ProgressBar pb_drop;
    Animation   anim_up;
    Animation   anim_down;
    boolean rotated = false;
    int headerHight;
    String up_time = "";

    public HeaderView(Context context) {
        super(context);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        headerHight = getResources().getDimensionPixelOffset(R.dimen.header_refresh_height);
        anim_up = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        anim_down = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        iv_drop = (ImageView) findViewById(R.id.iv_drop);
        tv_text = (TextView) findViewById(R.id.tv_drop_text);
        tv_time = (TextView) findViewById(R.id.tv_drop_time);
        pb_drop = (ProgressBar) findViewById(R.id.pb_drop);

    }

    /**
     * 刷新完成初始化组件
     */
    @Override
    public void onReset() {
        super.onReset();
        rotated = false;
        iv_drop.clearAnimation();
        iv_drop.setVisibility(INVISIBLE);
        pb_drop.setVisibility(INVISIBLE);

    }

    @Override
    public void onRelease() {
        super.onRelease();
    }

    /**
     * 刷新完成调用
     */
    @Override
    public void complete() {
        super.complete();
        rotated = false;
        iv_drop.clearAnimation();
        iv_drop.setVisibility(INVISIBLE);
        pb_drop.setVisibility(INVISIBLE);
        tv_text.setText("刷新完成");
    }

    /**
     * 下拉是调用
     */
    @Override
    public void onSwipe(int y, boolean isComplete) {
        super.onSwipe(y, isComplete);
        if (isComplete) {
            iv_drop.setVisibility(VISIBLE);
            if (y > headerHight) {
                tv_text.setText("松开刷新");
                tv_time.setText(up_time);
                if (!rotated) {
                    iv_drop.clearAnimation();
                    iv_drop.startAnimation(anim_up);
                    rotated = true;
                }
            } else if (y < headerHight) {
                if (rotated) {
                    iv_drop.clearAnimation();
                    iv_drop.startAnimation(anim_down);
                    rotated = false;
                }
                tv_text.setText("松开返回");
            }
        }
    }

    @Override
    public void onPrepare() {
        super.onPrepare();
    }

    /**
     * 下拉刷新 当action_up时调用
     */
    @Override
    public void onRefresh() {
        super.onRefresh();
        tv_text.setText("加载中...");
        up_time = TimeUtil.getTime();
        tv_time.setText(up_time);
        iv_drop.clearAnimation();
        iv_drop.setVisibility(INVISIBLE);
        pb_drop.setVisibility(VISIBLE);
    }
}
