package edu.hsl.myappnewsday.ui.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import edu.hsl.myappnewsday.common.util.JsonUtil;
import edu.hsl.myappnewsday.common.util.UrlUtil;

/**
 * Created by Administrator on 2016/5/30.
 */
public class BaseActivity extends AppCompatActivity {
    //    protected int      screen_w;
//    protected int      screen_h;
    public UrlUtil  mUtil;
    public JsonUtil mJsonUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        screen_w = getWindowManager().getDefaultDisplay().getWidth();
//        screen_h = getWindowManager().getDefaultDisplay().getHeight();
        mUtil = new UrlUtil();
        mJsonUtil = new JsonUtil();
        initView();
        initEvent();
        initData();
    }

    public void initEvent() {
    }

    public void initData() {
    }

    public void initView() {

    }

    public void startActivity(Class<?> activity) {

        startActivity(activity, null, null);
    }

    public void startActivity(Class<?> activity, Bundle bundle) {
        startActivity(activity, bundle, null);
    }

    public void startActivity(Class<?> activity, Uri uri) {
        startActivity(activity, null, uri);
    }

    public void startActivity(Class<?> activity, Bundle bundle, Uri uri) {
        Intent intent = new Intent(this, activity);
        if (bundle != null)
            intent.putExtras(bundle);
        if (uri != null)
            intent.setData(uri);
        startActivity(intent);
    }

    public void startActivity(String action, Bundle bundle) {
        startActivity(action, bundle, null);
    }

    public void startActivity(String action) {
        startActivity(action, null, null);
    }

    public void startActivity(String action, Bundle bundle, Uri uri) {
        Intent intent = new Intent(action);
        if (bundle != null)
            intent.putExtras(bundle);
        if (uri != null)
            intent.setData(uri);
        startActivity(intent);

    }

    public void share(String title, String text, String url, String imageUrl, String comment, String
            apkName) {
        ShareSDK.initSDK(this);
        OnekeyShare share = new OnekeyShare();
        share.disableSSOWhenAuthorize();
        if (title != null)
            share.setTitle(title);
        if (text != null)
            share.setText(text);
        if (url != null) {
            share.setUrl(url);
            share.setTitleUrl(url);
            share.setSiteUrl(url);
        }
        if (imageUrl != null)
            share.setImageUrl(imageUrl);
        if (comment != null)
            share.setComment(comment);
        if (apkName != null)
            share.setSite(apkName);
        share.show(this);
    }

    private Toast toast;

    public void toast(int resId) {
        toast(getString(resId));
    }

    private void toast(String msg) {
        if (toast == null) {
            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText(msg);
            toast.show();
        }
    }

//    public interface TouchListener {
//        public void onTouchEvent(MotionEvent event);
//
//        public boolean onInterceptTuochEvent(MotionEvent event);
//    }
//
//    private List<TouchListener> mTouchListeners = new ArrayList<>();
//
//    public void registerTouchListener(TouchListener listener) {
//        mTouchListeners.add(listener);
//    }
//
//    public void nuRegisterTuochListener(TouchListener listener) {
//        mTouchListeners.remove(listener);
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//
//        for (TouchListener listener : mTouchListeners) {
//            listener.onTouchEvent(ev);
//            if (!listener.onInterceptTuochEvent(ev)) {
//                return false;
//            }
//        }
//        return super.dispatchTouchEvent(ev);
//    }
}
