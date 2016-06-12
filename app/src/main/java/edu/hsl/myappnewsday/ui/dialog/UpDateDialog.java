package edu.hsl.myappnewsday.ui.dialog;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.flyco.dialog.widget.internal.BaseAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.hsl.myappnewsday.R;
import is.arontibo.library.ElasticDownloadView;

/**
 * Created by Administrator on 2016/6/7.
 */
public class UpDateDialog extends BaseAlertDialog<UpDateDialog> {

    @BindView(R.id.edv_load)
    ElasticDownloadView mEdvLoad;
    @BindView(R.id.tv_ver)
    TextView            mTvVer;
    @BindView(R.id.tv_ver_new)
    TextView            mTvVerNew;
    RequestQueue mQueue;
    boolean isfristload = true;
    @BindView(R.id.tv_tttt)
    TextView mTvTttt;
    @BindView(R.id.tv_install)
    TextView mTvInstall;

    /**
     * method execute order:
     * show:constrouctor---show---oncreate---onStart---onAttachToWindow
     * dismiss:dismiss---onDetachedFromWindow---onStop
     *
     * @param context
     */
    public UpDateDialog(Context context) {
        super(context);
        mQueue = Volley.newRequestQueue(mContext);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        this.setCancelable(false);
//    }

    @Override
    public View onCreateView() {
        View view = getLayoutInflater().inflate(R.layout.layout_update_view, null);
        ButterKnife.bind(this, view);
        mTvVer.setText(getVersionName());
        mTvInstall.setVisibility(View.GONE);
        mTvVerNew.setText("匹配中...");
        getVerNumber();//读取最新版本号
        return view;
    }

    private String getVersionName() {

        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName
                    (), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void getVerNumber() {
        StringRequest request = new StringRequest("http://www.baidu.com", new Response
                .Listener<String>() {
            @Override
            public void onResponse(String response) {
                mTvVerNew.setText("1.1.2");
                mEdvLoad.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTvVerNew.setText("网络错误");
                mTvTttt.setVisibility(View.VISIBLE);
            }
        });
        mQueue.add(request);
    }

    @OnClick({R.id.edv_load, R.id.tv_install})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edv_load:
                if (isfristload) {
                    isfristload = false;
                    mEdvLoad.startIntro();
                    new AsyncTask<String, Integer, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            for (int i = 0; i < 100; i++) {
                                if (i < 20 || i > 80) {
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    try {
                                        Thread.sleep(3);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                publishProgress(i);
                            }
                            return null;
                        }

                        @Override
                        protected void onProgressUpdate(Integer... values) {
                            mEdvLoad.setProgress(values[0]);
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            mEdvLoad.success();
                            mTvInstall.setVisibility(View.VISIBLE);
                        }
                    }.execute("");
                }
                break;
            case R.id.tv_install:
                dismiss();
                break;
        }
    }

}
