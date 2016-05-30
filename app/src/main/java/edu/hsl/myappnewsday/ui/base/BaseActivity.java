package edu.hsl.myappnewsday.ui.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/5/30.
 */
public class BaseActivity extends AppCompatActivity {
    protected int   screen_w;
    protected int   screen_h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen_w = getWindowManager().getDefaultDisplay().getWidth();
        screen_h = getWindowManager().getDefaultDisplay().getHeight();
        initView();
        initData();
        initEvent();
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

}
