package edu.hsl.myappnewsday.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.dialog.widget.internal.BaseAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.hsl.myappnewsday.R;

/**
 * Created by Administrator on 2016/6/6.+
 * 注册弹窗
 */
public class EnrollDialog extends BaseAlertDialog<EnrollDialog> {
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.et_pwds)
    EditText mEtPwds;
    @BindView(R.id.tv_back)
    TextView mTvBack;
    @BindView(R.id.tv_enroll)
    TextView mTvEnroll;

    /**
     * method execute order:
     * show:constrouctor---show---oncreate---onStart---onAttachToWindow
     * dismiss:dismiss---onDetachedFromWindow---onStop
     *
     * @param context
     */
    public EnrollDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        View view = getLayoutInflater().inflate(R.layout.layout_enroll_view, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.tv_back, R.id.tv_enroll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                MyDialog dialog = new MyDialog(mContext);
                dialog.showAnim(new BounceTopEnter());//弹窗动画
                dialog.dismissAnim(new FadeExit());//弹窗关闭动画
                dialog.show();
                this.dismiss();
                break;
            case R.id.tv_enroll:
                dismiss();
                break;
        }
    }
}
