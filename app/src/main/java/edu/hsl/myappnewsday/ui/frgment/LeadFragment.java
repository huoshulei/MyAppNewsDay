package edu.hsl.myappnewsday.ui.frgment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.common.util.PreserveUtil;
import edu.hsl.myappnewsday.ui.adapter.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeadFragment extends Fragment {
    ImageView icon1, icon2, icon3, icon4;
    ImageView[] image = {icon1, icon2, icon3, icon4};
    TextView         tv_skip;
    ViewPagerAdapter adapter;
    ViewPager        vp_lead;
    CartoonFragment  cartoon;

    public LeadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lead, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        tv_skip = (TextView) getActivity().findViewById(R.id.tv_skip);
        vp_lead = (ViewPager) getActivity().findViewById(R.id.vp_lead);
        adapter = new ViewPagerAdapter(getActivity());
        image[0] = (ImageView) getActivity().findViewById(R.id.iv_lead_1);
        image[1] = (ImageView) getActivity().findViewById(R.id.iv_lead_2);
        image[2] = (ImageView) getActivity().findViewById(R.id.iv_lead_3);
        image[3] = (ImageView) getActivity().findViewById(R.id.iv_lead_4);
        vp_lead.setAdapter(adapter);
        vp_lead.addOnPageChangeListener(getAddListener());
        initPagerView();
        tv_skip.setVisibility(View.INVISIBLE);
        tv_skip.setOnClickListener(getListener());
    }

    @NonNull
    private View.OnClickListener getListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSkip();
            }
        };
    }

    @NonNull
    private ViewPager.OnPageChangeListener getAddListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_skip.setVisibility(View.INVISIBLE);
                if (position > 2) {
                    tv_skip.setVisibility(View.VISIBLE);
                }
                for (ImageView imageView : image) {
                    imageView.setImageResource(R.mipmap.a41);
                }
                image[position].setImageResource(R.mipmap.a4);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    private void initSkip() {
        PreserveUtil.putBoolean(getActivity(), "isFirstRun", false);
        FragmentManager     manager     = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        cartoon = new CartoonFragment();
        transaction.replace(R.id.fl_lead, cartoon);
        transaction.commit();
    }

    private void initPagerView() {
        ImageView iv_lead;
        iv_lead = (ImageView) getActivity().getLayoutInflater().inflate(R.layout
                .layout_item_lead, null);
        iv_lead.setImageResource(R.mipmap.welcome);
        adapter.addViewData(iv_lead);
        iv_lead = (ImageView) getActivity().getLayoutInflater().inflate(R.layout
                .layout_item_lead, null);
        iv_lead.setImageResource(R.mipmap.wy);
        adapter.addViewData(iv_lead);
        iv_lead = (ImageView) getActivity().getLayoutInflater().inflate(R.layout
                .layout_item_lead, null);
        iv_lead.setImageResource(R.mipmap.bd);
        adapter.addViewData(iv_lead);
        iv_lead = (ImageView) getActivity().getLayoutInflater().inflate(R.layout
                .layout_item_lead, null);
        iv_lead.setImageResource(R.mipmap.small);
        adapter.addViewData(iv_lead);
        adapter.notifyDataSetChanged();
    }

}
