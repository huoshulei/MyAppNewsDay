package edu.hsl.myappnewsday.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.bean.NewsBean;
import edu.hsl.myappnewsday.common.util.SerializableUtil;
import edu.hsl.myappnewsday.ui.base.BaseActivity;
import edu.hsl.myappnewsday.ui.dialog.MenuDialog;
import edu.hsl.myappnewsday.ui.snake.TumblrRelativeLayout;

public class WebActivity extends BaseActivity {
    WebView              mWebView;
    TumblrRelativeLayout trl_menu;
    FragmentManager      mFragmentManager;
    //    private String mUrl;
    MenuDialog           mDialog;
    NewsBean.Data        mData;
    //    FavoriteNews  favNews;
    RequestQueue         mQueue;

    @Override
    public void initView() {
        setContentView(R.layout.activity_web);
        mFragmentManager = getSupportFragmentManager();
        initDialog();
        Bundle bundle = getIntent().getExtras();
//        mUrl = bundle.getString("URL");
        mData = bundle.getParcelable("DATA");
        mWebView = (WebView) findViewById(R.id.wv_news);
        WebSettings settings = mWebView.getSettings();
        trl_menu = (TumblrRelativeLayout) findViewById(R.id.trl_menu);
        /**蛇形动画的点击事件*/
        trl_menu.setMenuListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                trl_menu.getImageViewList();
                if (mFragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mDialog.getDialogFragment().show(mFragmentManager, ContextMenuDialogFragment
                            .TAG);
                }

            }
        });
        //这几个是设置 平铺和缩进的
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        super.initView();
    }

    /**
     * 弹窗的点击事件
     */
    private void initDialog() {
        mDialog = new MenuDialog(getApplicationContext(), mFragmentManager) {
            @Override
            public void MenuItemClick(View clickedView, int position) {
                trl_menu.setImageViewList();
                switch (position) {
                    case 0:
                        share(mData.getTitle(), mData.getSummary(), mData.getLink(), mData.getIcon()
                                , null, getPackageName());
//                        favNews.favNews();
                        break;
                    case 1:
                        if (mQueue == null)
                            mQueue = Volley.newRequestQueue(getApplicationContext());
                        SerializableUtil.getBitmap(WebActivity.this,
                                mData, mQueue);
//                        if (serialize != null)
//                            Toast.makeText(WebActivity.this, "收藏成功!", Toast.LENGTH_SHORT).show();
//                        PreserveUtil.putString(getApplicationContext(), mData.getNid(),
//                                serialize);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }
        };
    }

    /**
     * 加载网页数据
     */
    @Override
    public void initData() {
//        Log.d(TAG, "initData: "+mUrl);
        super.initData();
        mWebView.loadUrl(mData.getLink());
    }


}
