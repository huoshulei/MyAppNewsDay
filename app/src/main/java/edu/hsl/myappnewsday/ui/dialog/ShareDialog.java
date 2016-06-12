package edu.hsl.myappnewsday.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.flyco.dialog.widget.base.TopBaseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.hsl.myappnewsday.R;

/**
 * Created by Administrator on 2016/6/12.
 */
public class ShareDialog extends TopBaseDialog<ShareDialog> {
    @BindView(R.id.ll_share_qq)
    LinearLayout mLlShareQq;
    @BindView(R.id.ll_share_friend)
    LinearLayout mLlShareFriend;
    @BindView(R.id.ll_share_weixin)
    LinearLayout mLlShareWeixin;
    @BindView(R.id.ll_share_weibo)
    LinearLayout mLlShareWeibo;

    public ShareDialog(Context context, View animateView) {
        super(context, animateView);
    }

    public ShareDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        View view = getLayoutInflater().inflate(R.layout.layout_share_dialog, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setUiBeforShow() {

    }

    @OnClick({R.id.ll_share_qq, R.id.ll_share_friend, R.id.ll_share_weixin, R.id.ll_share_weibo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_share_qq:
                dismiss();
                break;
            case R.id.ll_share_friend:
                dismiss();
                break;
            case R.id.ll_share_weixin:
                dismiss();
                break;
            case R.id.ll_share_weibo:
                dismiss();
                break;
        }
    }
}
