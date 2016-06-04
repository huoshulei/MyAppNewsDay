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
    RelativeLayout fl_main;
    RelativeLayout fl_left;
    RelativeLayout fl_right;
    ImageView      iv_home;
    ImageView      iv_share;
    boolean isFirst        = true;
    boolean isFirstKeyBack = true;
    public int newsId = 0;
    LeftFragment                fragmentLeft;
    RightFragment               fragmentRight;
    RelativeLayout.LayoutParams layoutParams;
    RelativeLayout.LayoutParams layoutParams_left;
    RelativeLayout.LayoutParams layoutParams_right;
    public MainFragment mMainFragment;
    float x1 = 0, x2 = 0;//点击屏幕按下与弹起点的坐标
    float y1 = 0, y2 = 0;
    int width;
    float praentX = 0;//获得父布局相对于屏幕的偏移量
    private int width_left;
    private int width_right;

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
            layoutParams_left = (RelativeLayout.LayoutParams) fl_left.getLayoutParams();
            layoutParams_right = (RelativeLayout.LayoutParams) fl_right.getLayoutParams();
            layoutParams.width = width;
            fl_main.setLayoutParams(layoutParams);
            isFirst = false;
        }
        return true;
    }

    public void setRight(int distance) {
        if (distance > 0 && distance < width_right) {
            layoutParams.rightMargin = distance;
            layoutParams.leftMargin = -distance;
            layoutParams_right.rightMargin = (distance - width_right) / 2;
            fl_right.setLayoutParams(layoutParams_right);
            fl_main.setLayoutParams(layoutParams);
        } else if (distance == 0) {
            addRightFragment();
            setRight(distance, width_right);
        }
    }

    public void setRight(int distance, int width_right) {
        if (distance <= width_right) {
            layoutParams_right.rightMargin = (distance - width_right) / 2;
            layoutParams.leftMargin = -distance;
            layoutParams.rightMargin = distance;
            fl_right.setLayoutParams(layoutParams_right);
            fl_main.setLayoutParams(layoutParams);
            if (distance < width_right) {
                distance += 1;
                setRight(distance, width_right);
            }
            isFirstKeyBack = true;
        }
    }

    private void addRightFragment() {
        FragmentManager     fm          = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (fragmentRight == null)
            fragmentRight = new RightFragment();
        transaction.replace(R.id.fl_right, fragmentRight);
        transaction.commit();
        width_right = fl_right.getWidth();
        layoutParams_right.rightMargin = -width_right / 2;
        fl_right.setLayoutParams(layoutParams_right);
    }

    /**
     * 主布局设置偏移量
     */
    public void setLeft(int distance) {
        if (distance > 0 && distance < width_left) {
            layoutParams.rightMargin = -distance;
            layoutParams.leftMargin = distance;
            layoutParams_left.leftMargin = (distance - width_left) / 2;
            fl_left.setLayoutParams(layoutParams_left);
            fl_main.setLayoutParams(layoutParams);
        } else if (distance == 0) {
            addLeftFragment();
            setLeft(distance, width_left);
        }

    }

    /**
     * 加载左侧菜单栏
     */
    private void addLeftFragment() {
        FragmentManager     fm          = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (fragmentLeft == null) {
            fragmentLeft = new LeftFragment();
        }
        transaction.replace(R.id.fl_left, fragmentLeft);
//        transaction.addToBackStack("");
        transaction.commit();
        width_left = fl_left.getWidth();
        layoutParams_left.leftMargin = -width_left / 2;
        fl_left.setLayoutParams(layoutParams_left);
    }

    public void setLeft(int distance, int width_left) {
//        for (int i = distance; i < width_left; i += 5) {
        if (distance <= width_left) {
            layoutParams_left.leftMargin = (distance - width_left) / 2;
            layoutParams.leftMargin = distance;
            layoutParams.rightMargin = -distance;
            fl_left.setLayoutParams(layoutParams_left);
            fl_main.setLayoutParams(layoutParams);
            if (distance < width_left) {
                distance += 1;
                setLeft(distance, width_left);
            }
//            new AsyncTask<Integer, Void, Integer>() {
//                @Override
//                protected Integer doInBackground(Integer... params) {
////                    try {
////                        Thread.sleep(10);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
//                    int distance = params[0] + 1;
//                    if (distance < width_left) {
//                        return distance;
//                    } else if (distance >= width_left) {
//                        return distance;
//                    }
//                    return distance;
//                }

//                @Override
//                protected void onPostExecute(Integer integer) {
//                    super.onPostExecute(integer);
//                    setLeft(integer, width_left);
//                }
//            }.execute(distance);
//        }
//        }
            isFirstKeyBack = true;
        }
    }

    /**
     * 恢复主布局状态
     */
    public void initLocation(int distance) {
        if (distance > 0) {
            if (width_left > 0) {
                layoutParams.rightMargin = -width_left + distance;
                layoutParams.leftMargin = width_left - distance;
                layoutParams_left.leftMargin = -distance / 2;
                fl_main.setLayoutParams(layoutParams);
                fl_left.setLayoutParams(layoutParams_left);
            }
            if (width_right > 0) {
                layoutParams.leftMargin = -width_right + distance;
                layoutParams.rightMargin = width_right - distance;
                layoutParams_right.rightMargin = -distance / 2;
                fl_right.setLayoutParams(layoutParams_right);
                fl_main.setLayoutParams(layoutParams);
            }
        }
        if (distance == 0) {
            initLocation(0, true);
        }
    }

    public void initLocation(int distance, boolean isLocantion) {
        if (width_left > 0) {
            layoutParams.rightMargin = -width_left + distance;
            layoutParams.leftMargin = width_left - distance;
            layoutParams_left.leftMargin = -distance / 2;
            fl_main.setLayoutParams(layoutParams);
            fl_left.setLayoutParams(layoutParams_left);
            if (distance < width_left) {
                distance += 1;
                initLocation(distance, isLocantion);
            }
        }
        if (width_right > 0) {
            layoutParams.leftMargin = -width_right + distance;
            layoutParams.rightMargin = width_right - distance;
            layoutParams_right.rightMargin = -distance / 2;
            fl_right.setLayoutParams(layoutParams_right);
            fl_main.setLayoutParams(layoutParams);
            if (distance < width_right) {
                distance += 1;
                initLocation(distance, isLocantion);
            }
        }
        initFragment();

    }

    private void initFragment() {
        layoutParams.rightMargin = 0;
        layoutParams.leftMargin = 0;
        FragmentManager     fm          = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (fragmentLeft != null) {
            transaction.remove(fragmentLeft);
        }
        if (fragmentRight != null) {
            transaction.remove(fragmentRight);
        }
        transaction.commit();
        fl_main.setLayoutParams(layoutParams);
        isFirstKeyBack = false;
        width_right = 0;
        width_left = 0;
    }

    @Override
    public void initEvent() {
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRight(0);
            }
        });
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLeft(0);
            }
        });
        super.initEvent();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isFirstKeyBack) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                initLocation(0);
            }
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
                if (x1 < 150 && praentX == 0) {//左菜单加载
                    addLeftFragment();
                }
                if (x1 > width - 150 && praentX == 0) {//右菜单加载
                    addRightFragment();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                x2 = event.getX();
                if (x2 > x1 && x1 < 150 && praentX == 0) {//左菜单滑出
                    setLeft((int) (x2 - x1));
                }
                if (x1 > x2 && x1 > width - 150 && praentX == 0) {//右菜单滑出
                    setRight((int) (x1 - x2));
                }
                if (x2 < x1 && x1 - praentX < 150 && praentX != 0) {//左菜单返回
                    initLocation((int) (x1 - x2));
                }
                if (x1 < x2 && x1 - praentX > width - 150 && praentX != 0) {//右菜单返回
                    initLocation((int) (x2 - x1));
                }
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                if (Math.abs(x1 - x2) > Math.abs(y1 - y2)) {
                    if (x1 - x2 >= 300 && x1 - praentX > width - 150) {//右菜单滑出成功
                        Log.d(TAG, "dispatchTouchEvent: 右菜单滑出成功");
                        setRight((int) (x1 - x2), width_right);
                        return false;
                    }
                    if (x2 - x1 >= 300 && x1 - praentX > width - 150) {//右菜单返回成功
                        Log.d(TAG, "dispatchTouchEvent: 右菜单返回成功");
                        initLocation((int) (x2 - x1), true);
                        return false;
                    }
                    if (x1 - x2 > 0 && x1 - x2 < 300 && x1 - praentX > width - 150) {//右菜单滑出失败
                        Log.d(TAG, "dispatchTouchEvent: 右菜单滑出失败");
                        initLocation(width_right - (int) (x1 - x2), true);
                        return false;
                    }
                    if (x2 - x1 > 0 && x2 - x1 < 300 && x1 - praentX > width - 150) {//右菜单返回失败
                        Log.d(TAG, "dispatchTouchEvent: 右菜单返回失败");
                        setRight(width_right - (int) (x2 - x1), width_right);
                        return false;
                    }

                    if (x2 - x1 > 0 && x2 - x1 < 300 && x1 - praentX < 150) {//左菜单滑出失败
                        Log.d(TAG, "dispatchTouchEvent: 左菜单滑出失败");
                        initLocation((int) (x2 - x1), true);
                        return false;
                    }
                    if (x2 - x1 >= 300 && x1 - praentX < 150) {//左菜单滑出成功
                        Log.d(TAG, "dispatchTouchEvent: 左菜单滑出成功");
                        setLeft((int) (x2 - x1), width_left);
                        return false;
                    }
                    if (x1 - x2 >= 300 && x1 - praentX < 150) {//左菜单返回成功
                        Log.d(TAG, "dispatchTouchEvent: 左菜单返回成功");
                        initLocation((int) (x1 - x2), true);
                        return false;
                    }
                    if (x1 - x2 > 0 && x1 - x2 < 300 && x1 - praentX < 150) {//左菜单返回失败
                        Log.d(TAG, "dispatchTouchEvent: 左菜单返回成功");
                        setLeft(width_left - (int) (x1 - x2), width_left);
                        return false;
                    }

//                    return false;
                }
                break;
        }

        return super.dispatchTouchEvent(event);
    }


}