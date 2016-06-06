package edu.hsl.myappnewsday.ui.frgment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

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
                        if (mFavoriteFragment != mMainActivity.currentFragment) {
                            if (mFavoriteFragment == null)
                                mFavoriteFragment = new FavoriteFragment();
                            mTransaction.replace(R.id.fl_news, mFavoriteFragment);
                            mTransaction.commit();
                            mMainActivity.currentFragment = mFavoriteFragment;
                        }
                        break;
                    case R.id.rl_comment:
                        if (mCommentFragment != mMainActivity.currentFragment) {
                            if (mCommentFragment == null)
                                mCommentFragment = new CommentFragment();
                            mTransaction.replace(R.id.fl_news, mCommentFragment);
                            mTransaction.commit();
                            mMainActivity.currentFragment = mCommentFragment;
                        }
                        break;
                    case R.id.rl_local:
                        if (mLocalFragment != mMainActivity.currentFragment) {
                            if (mLocalFragment == null)
                                mLocalFragment = new LocalFragment();
                            mTransaction.replace(R.id.fl_news, mLocalFragment);
                            mTransaction.commit();
                            mMainActivity.currentFragment = mLocalFragment;
                        }
                        break;
                    case R.id.rl_photo:
                        if (mPhotoFragment != mMainActivity.currentFragment) {
                            if (mPhotoFragment == null)
                                mPhotoFragment = new PhotoFragment();
                            mTransaction.replace(R.id.fl_news, mPhotoFragment);
                            mTransaction.commit();
                            mMainActivity.currentFragment = mPhotoFragment;
                        }
                        break;
                }

                mMainActivity.initLocation(0);
            }
        };
    }
}
