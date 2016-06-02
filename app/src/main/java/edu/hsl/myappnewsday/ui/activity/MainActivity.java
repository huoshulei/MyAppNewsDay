package edu.hsl.myappnewsday.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.ui.base.BaseActivity;
import edu.hsl.myappnewsday.ui.frgment.LeftFragment;
import edu.hsl.myappnewsday.ui.frgment.MainFragment;
import edu.hsl.myappnewsday.ui.frgment.RightFragment;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    public RelativeLayout fl_main;
    RelativeLayout fl_left;
    RelativeLayout fl_right;
    ImageView      iv_home;
    ImageView      iv_share;
    boolean isFirst = true;
    public int newsId = 0;
    LeftFragment                fragmentLeft;
    RightFragment               fragmentRight;
    RelativeLayout.LayoutParams layoutParams;
    public MainFragment mMainFragment;
    float x1 = 0, x2 = 0;//点击屏幕按下与弹起点的坐标
    float y1 = 0, y2 = 0;
    int width;
    float praentX = 0;//获得父布局相对于屏幕的偏移量

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
//        ll_main = new MainFragment().getFl_main();
        fl_main = (RelativeLayout) findViewById(R.id.ll_main);
        fl_left = (RelativeLayout) findViewById(R.id.ll_left);
        fl_right = (RelativeLayout) findViewById(R.id.ll_right);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_share = (ImageView) findViewById(R.id.iv_share);
        onPreDraw();
        super.initView();
    }

    @Override
    public void initData() {
        FragmentManager     fm          = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mMainFragment = new MainFragment();
        transaction.replace(R.id.fl_main, mMainFragment);
        transaction.commit();
        super.initData();
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        width = metrics.widthPixels;
    }

    /**
     * 判断是否首次加载当前activity 如果是获取当前屏幕宽度 并锁定主布局宽度
     * 否则跳过
     * 返回值无意义
     */
    public boolean onPreDraw() {
        if (isFirst) {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            width = metrics.widthPixels;
            layoutParams = (RelativeLayout.LayoutParams) fl_main
                    .getLayoutParams();
            layoutParams.width = width;
            fl_main.setLayoutParams(layoutParams);
            isFirst = false;
        }
        return true;
    }

    public void setRight() {
        FragmentManager     fm          = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (fragmentRight == null)
            fragmentRight = new RightFragment();
        transaction.replace(R.id.fl_right, fragmentRight);
//        transaction.addToBackStack("");
        transaction.commit();
        int width = fl_right.getWidth();
        layoutParams.leftMargin = -width;
        layoutParams.rightMargin = width;
        fl_main.setLayoutParams(layoutParams);

    }

    /**
     * 主布局设置偏移量
     */
    public void setLeft() {
        FragmentManager     fm          = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (fragmentLeft == null) {
            fragmentLeft = new LeftFragment();
        }
        transaction.replace(R.id.fl_left, fragmentLeft);
//        transaction.addToBackStack("");
        transaction.commit();
        int width = fl_left.getWidth();
        layoutParams.leftMargin = width;
        layoutParams.rightMargin = -width;
        fl_main.setLayoutParams(layoutParams);
    }

    /**
     * 恢复主布局状态
     */
    public void initLocation() {
        layoutParams.leftMargin = 0;
        layoutParams.rightMargin = 0;
        fl_main.setLayoutParams(layoutParams);
        FragmentManager     fm          = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (fragmentLeft != null) {
            transaction.remove(fragmentLeft);
        }
        if (fragmentRight != null) {
            transaction.remove(fragmentRight);
        }
        transaction.commit();
    }

    @Override
    public void initEvent() {
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRight();
            }
        });
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLeft();
            }
        });
        super.initEvent();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            initLocation();
        }
        return super.onKeyDown(keyCode, event);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
////                praentX = mMainActivity.fl_main.getLeft();//获得子布局相对于屏幕的偏移量
//                x1 = event.getX();
//                Log.d(TAG, "onTouchEvent: 按下" + x1);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = event.getX();
//                Log.d(TAG, "onTouchEvent: 弹起" + x2);
//                if (x1 - x2 > 300 && x1 > width - 200) {
//                    setRight();
//                    return false;
//                }
//                if (x2 - x1 > 300 && x1 > width - 200) {
//                    initLocation();
//                    return false;
//                }
//                if (x2 - x1 > 300 && x1 < 200) {
//                    setLeft();
//                    return false;
//                }
//                if (x1 - x2 > 300 && x1 < 200) {
//                    initLocation();
//                    return false;
//                }
//                break;
//        }
//        return super.onTouchEvent(event);
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                praentX = fl_main.getLeft();
                Log.d(TAG, "onTouchEvent: 1x按下" + x1);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                if (Math.abs(x1 - x2) > Math.abs(y1 - y2)) {
                    if (x1 - x2 > 300 && x1 - praentX > width - 200) {
                        setRight();
                        return false;
                    }
                    if (x2 - x1 > 300 && x1 - praentX > width - 200) {
                        initLocation();
                        return false;
                    }
                    if (x2 - x1 > 300 && x1 - praentX < 200) {
                        setLeft();
                        return false;
                    }
                    if (x1 - x2 > 300 && x1 - praentX < 200) {
                        initLocation();
                        return false;
                    }

                    Log.d(TAG, "onTouchEvent: 1x弹起" + x2);
//                    return false;
                }
                break;
        }

        Log.d(TAG, "dispatchTouchEvent: 我什么时候走的");
        return super.dispatchTouchEvent(event);
    }


}