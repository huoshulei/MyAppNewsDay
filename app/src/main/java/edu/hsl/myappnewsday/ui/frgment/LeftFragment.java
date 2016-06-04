package edu.hsl.myappnewsday.ui.frgment;


import android.app.Fragment;
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
    RelativeLayout rl_news;
    RelativeLayout rl_favorite;
    RelativeLayout rl_local;
    RelativeLayout rl_comment;
    RelativeLayout rl_photo;
    MainFragment   mMainFragment;
    MainActivity   mMainActivity;

    public LeftFragment() {
        // Required empty public constructor
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
        mMainActivity = (MainActivity) getActivity();
        mMainFragment = mMainActivity.mMainFragment;
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
                        mMainActivity.newsId = 0;
                        break;
                    case R.id.rl_favorite:
                        mMainActivity.newsId = 1;
                        break;
                    case R.id.rl_comment:
                        mMainActivity.newsId = 3;
                        break;
                    case R.id.rl_local:
                        mMainActivity.newsId = 2;
                        break;
                    case R.id.rl_photo:
                        mMainActivity.newsId = 4;
                        break;
                }

                mMainActivity.initLocation(0);
            }
        };
    }
}
