package edu.hsl.myappnewsday.ui.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.ui.base.BaseActivity;

public class WebActivity extends BaseActivity {
    WebView mWebView;
    private String mUrl;
//    private static final String TAG = "WebActivity";

    @Override
    public void initView() {
        setContentView(R.layout.activity_web);
        Bundle bundle = getIntent().getExtras();
        mUrl = bundle.getString("URL");
        mWebView = (WebView) findViewById(R.id.wv_news);
        WebSettings settings = mWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        super.initView();
    }

    @Override
    public void initData() {
//        Log.d(TAG, "initData: "+mUrl);
        super.initData();
        mWebView.loadUrl(mUrl);
    }
}
