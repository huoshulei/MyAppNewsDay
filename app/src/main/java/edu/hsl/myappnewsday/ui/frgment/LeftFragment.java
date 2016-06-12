package edu.hsl.myappnewsday.ui.frgment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Arrays;

import edu.hsl.myappnewsday.Arr;
import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.ui.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeftFragment extends Fragment {
    private static final String TAG = "LeftFragment";
    RelativeLayout      rl_news;
    RelativeLayout      rl_favorite;
    RelativeLayout      rl_local;
    RelativeLayout      rl_comment;
    RelativeLayout      rl_photo;
    MainActivity        mMainActivity;
    FragmentTransaction mTransaction;
    FavoriteFragment    mFavoriteFragment;
    CommentFragment     mCommentFragment;
    LocalFragment       mLocalFragment;
    PhotoFragment       mPhotoFragment;

    public LeftFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mMainActivity = (MainActivity) getActivity();
        mTransaction = mMainActivity.getFragmentTransaction();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_left, container, false);
        rl_news = (RelativeLayout) view.findViewById(R.id.rl_news);
        rl_favorite = (RelativeLayout) view.findViewById(R.id.rl_favorite);
        rl_local = (RelativeLayout) view.findViewById(R.id.rl_local);
        rl_comment = (RelativeLayout) view.findViewById(R.id.rl_comment);
        rl_photo = (RelativeLayout) view.findViewById(R.id.rl_photo);
        return view;
    }

    @Override
    public void onResume() {
        rl_comment.setOnClickListener(getListener());
        rl_news.setOnClickListener(getListener());
        rl_favorite.setOnClickListener(getListener());
        rl_local.setOnClickListener(getListener());
        rl_photo.setOnClickListener(getListener());
        super.onResume();
    }

    @NonNull
    private View.OnClickListener getListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.rl_news:

                        if (mMainActivity.mNewsFragment != mMainActivity.currentFragment) {
//                            if (mMainActivity.mNewsFragment == null) {
//                                mMainActivity.mNewsFragment = new NewsFragment();
//                            }
//                            mMainActivity.currentFragment = mMainActivity.mNewsFragment;
//                            mTransaction.replace(R.id.fl_news, mMainActivity.mNewsFragment);
//                            mTransaction.commit();
//                            Log.d(TAG, "onClick: 什么时候走的");
                            Fragment fragment = mMainActivity.mNewsFragment;
                            if (fragment == null)
                                fragment = new NewsFragment();
                            mTransaction.replace(R.id.fl_news, fragment);
                            mTransaction.commit();
                            mMainActivity.currentFragment = mMainActivity.mNewsFragment;
                        }
                        break;
                    case R.id.rl_favorite:
                        Fragment fragment = mFavoriteFragment;
                        if (fragment != mMainActivity.currentFragment) {
                            if (fragment == null)
                                fragment = new FavoriteFragment();
                            mTransaction.replace(R.id.fl_news, fragment);
                            mTransaction.addToBackStack(null);
                            mTransaction.commit();
                            mMainActivity.currentFragment = fragment;
                        }
                        break;
                    case R.id.rl_comment:
                        gotoFragment(mCommentFragment);
                        break;
                    case R.id.rl_local:
                        gotoFragment(mLocalFragment);
                        break;
                    case R.id.rl_photo:
//                        Log.d(TAG, "我就是看看结果 " + Arrays.toString(Arr.arr(new int[]{1, 2, 3, 4, 5,
//                                6, 7, 8, 9, 10})));
                        Toast.makeText(getActivity(), Arrays.toString(Arr.arr(new int[]{1, 2, 3,
                                4, 5, 6, 7, 8, 9, 10})), Toast.LENGTH_SHORT).show();
//                        gotoFragment(mPhotoFragment);
                        break;
                }

                mMainActivity.initLocation(0);
            }
        };
    }

    private void gotoFragment(Fragment fragment) {
        if (fragment != mMainActivity.currentFragment) {
            if (fragment == null)
                fragment = new CommentFragment();
            mTransaction.replace(R.id.fl_news, fragment);
            mTransaction.addToBackStack(null);
            mTransaction.commit();
            mMainActivity.currentFragment = fragment;
        }
    }
}
