package edu.hsl.myappnewsday.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.FlipEnter.FlipTopEnter;
import com.flyco.animation.FlipExit.FlipVerticalExit;
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
    protected EditText mEtName;
    @BindView(R.id.et_pwd)
    protected EditText mEtPwd;
    @BindView(R.id.tv_enroll)
    protected TextView mTvEnroll;
    @BindView(R.id.tv_landing)
    protected TextView mTvLanding;

    public MyDialog(Context context) {
        super(context);
    }

    /**
     * 填充布局
     */
    @Override
    public View onCreateView() {

        View view = getLayoutInflater().inflate(R.layout.layout_land_view, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick({R.id.tv_enroll, R.id.tv_landing})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_enroll://注册
                EnrollDialog dialog = new EnrollDialog(mContext);
                dialog.showAnim(new FlipTopEnter());
                dialog.dismissAnim(new FlipVerticalExit());
                dialog.show();
                this.dismiss();
                break;
            case R.id.tv_landing://登陆
                Toast.makeText(mContext, "dianle ma ", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
        }
    }
}
