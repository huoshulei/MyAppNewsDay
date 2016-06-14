package edu.hsl.myappnewsday.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.bean.NewsBean;
import edu.hsl.myappnewsday.ui.base.BaseActivity;
import edu.hsl.myappnewsday.ui.dialog.MenuDialog;
import edu.hsl.myappnewsday.ui.snake.TumblrRelativeLayout;

public class WebActivity extends BaseActivity {
    WebView              mWebView;
    TumblrRelativeLayout trl_menu;
    FragmentManager      mFragmentManager;
    private String mUrl;
    MenuDialog    mDialog;
    NewsBean.Data mData;

    //    private static final String TAG = "WebActivity";
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
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        super.initView();
    }

    private void initDialog() {
        mDialog = new MenuDialog(getApplicationContext(), mFragmentManager) {
            @Override
            public void MenuItemClick(View clickedView, int position) {
                trl_menu.setImageViewList();
                switch (position) {
                    case 0:

                        break;
                    case 1:
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
                Toast.makeText(WebActivity.this, "点你妹啊!!!" + position, Toast.LENGTH_SHORT).show();
            }
        };
    }


    @Override
    public void initData() {
//        Log.d(TAG, "initData: "+mUrl);
        super.initData();
        mWebView.loadUrl(mData.getLink());
    }

}
