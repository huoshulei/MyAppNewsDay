package edu.hsl.myappnewsday.ui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.flyco.dialog.widget.internal.BaseAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.hsl.myappnewsday.R;

/**
 * Created by Administrator on 2016/6/6.
 */
public class MyDialog extends BaseAlertDialog<MyDialog> {
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.tv_enroll)
    TextView mTvEnroll;
    @BindView(R.id.tv_landing)
    TextView mTvLanding;
    /**
     * 标题下划线
     */
    private View mVLineTitle;
    /**
     * 垂直线之间的btn
     */
    private View mVLineVertical;
    /**
     * 垂直线之间的btn
     */
    private View mVLineVertical2;
    /**
     * 水平方向的btn
     */
    private View mVLineHorizontal;
    /**
     * 标题下划线颜色
     */
    private int   mTitleLineColor  = Color.parseColor("#61AEDC");
    /**
     * 标题下划线高度
     */
    private float mTitleLineHeight = 1f;
    /**
     * 对话框之间的分割线颜色(水平+垂直)
     */
    private int   mDividerColor    = Color.parseColor("#DCDCDC");

    public static final int STYLE_ONE = 0;
    public static final int STYLE_TWO = 1;
    private             int mStyle    = STYLE_ONE;

    public MyDialog(Context context) {
        super(context);
//        /** 默认格式*/
//        /** 标题颜色 */
//        mTitleTextColor = Color.parseColor("#61AEDC");
//        /** 标题字体大小,单位sp */
//        mTitleTextSize = 22f;
//        /** 正文字体颜色 */
//        mContentTextColor = Color.parseColor("#383838");
//        /** 正文字体大小 */
//        mContentTextSize = 17f;
//        /** 按钮字体颜色(左 右 中) */
//        mLeftBtnTextColor = Color.parseColor("#8a000000");
//        mRightBtnTextColor = Color.parseColor("#8a000000");
//        mMiddleBtnTextColor = Color.parseColor("#8a000000");
    }

    /**
     * 填充布局
     */
    @Override
    public View onCreateView() {

//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams
//                .MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        /**标题栏*/
//        LinearLayout.LayoutParams titleParams = params;
//        titleParams.gravity = Gravity.CENTER;
//        mTvTitle.setLayoutParams(titleParams);
//        mLlContainer.addView(mTvTitle);
//        /**标题下划线*/
//        mVLineTitle = new View(mContext);
//        mLlContainer.addView(mVLineTitle);
//        mTvContent.setLayoutParams(params);
        View view = getLayoutInflater().inflate(R.layout.layout_land_view, null);
        ButterKnife.bind(this, view);
//        mTvEnroll = (TextView) view.findViewById(R.id.tv_enroll);
        return view;
    }

    @Override
    public void setUiBeforShow() {
//        mTvEnroll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
    }

    @OnClick({R.id.et_name, R.id.et_pwd, R.id.tv_enroll, R.id.tv_landing})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_name:
                break;
            case R.id.et_pwd:
                break;
            case R.id.tv_enroll:
                dismiss();
                break;
            case R.id.tv_landing:
                dismiss();
                break;
        }
    }
}
