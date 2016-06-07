package edu.hsl.myappnewsday.ui.frgment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.FallEnter.FallRotateEnter;
import com.flyco.animation.Jelly;
import com.flyco.animation.ZoomEnter.ZoomInTopEnter;
import com.flyco.animation.ZoomExit.ZoomOutBottomExit;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.ui.activity.MainActivity;
import edu.hsl.myappnewsday.ui.dialog.MyDialog;
import edu.hsl.myappnewsday.ui.dialog.UpDateDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class RightFragment extends Fragment {
    private static final String TAG = "RightFragment";
    ImageView    user_icon;
    TextView     tv_land;
    TextView     tv_update;
    TextView     tv_share;
    MainActivity mMainActivity;

    public RightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainActivity = (MainActivity) getActivity();
        return inflater.inflate(R.layout.fragment_right, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        user_icon = (ImageView) view.findViewById(R.id.iv_user_icon);
        tv_land = (TextView) view.findViewById(R.id.tv_landing);
        tv_update = (TextView) view.findViewById(R.id.tv_up_data);
        tv_share = (TextView) view.findViewById(R.id.tv_share);
        initEvent();
    }

    private void initEvent() {
        user_icon.setOnClickListener(getListener());
        tv_share.setOnClickListener(getListener());
        tv_land.setOnClickListener(getListener());
        tv_update.setOnClickListener(getListener());
    }

    @NonNull
    private View.OnClickListener getListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_user_icon:
                        /**此处可以设置头像相关功能*/
//                        break;
                    case R.id.tv_landing:
                        MyDialog dialog = new MyDialog(getActivity());
                        dialog.showAnim(new ZoomInTopEnter());
                        dialog.dismissAnim(new ZoomOutBottomExit());
                        dialog.show();
                        break;
                    case R.id.tv_up_data:
                        UpDateDialog upDateDialog = new UpDateDialog(getActivity());
                        upDateDialog.showAnim(new FallRotateEnter());
                        upDateDialog.dismissAnim(new Jelly());
                        upDateDialog.show();
                        break;
                    case R.id.tv_share:
                        break;
                }
                mMainActivity.initLocation(0);
            }
        };
    }

}
