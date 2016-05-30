package edu.hsl.myappnewsday.ui.frgment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.ui.activity.LeadActivity;
import edu.hsl.myappnewsday.ui.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartoonFragment extends Fragment {

    ImageView    mImageView;
    LeadActivity mLeadActivity;
//    MainFragment main;

    public CartoonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLeadActivity = (LeadActivity) getActivity();
        return inflater.inflate(R.layout.fragment_cartoon, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mImageView = (ImageView) getActivity().findViewById(R.id.iv_logo);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLeadActivity.startActivity(MainActivity.class);
//                FragmentManager     manager     = getFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                main = new MainFragment();
//                transaction.replace(R.id.fl_lead, main);
//                transaction.commit();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mImageView.startAnimation(animation);
    }

}

