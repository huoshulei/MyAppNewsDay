package edu.hsl.myappnewsday.ui.frgment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.ui.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    TabLayout    tabs;
    //    Fragment  mFragment;
    Adapter      mAdapter;
    ViewPager    mViewPager;
    MainActivity mMainActivity;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new Adapter(getChildFragmentManager());
        String[] title = getTitle();
        mMainActivity = (MainActivity) getActivity();
        for (int i = 0; i < title.length; i++) {
            mAdapter.addFragment(getFragment(i), title[i]);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainActivity.tv_title.setText("资讯");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_news);
        tabs = (TabLayout) view.findViewById(R.id.tabs);
        mViewPager.setAdapter(mAdapter);
        tabs.setupWithViewPager(mViewPager);
    }


    /**
     * 新闻分类
     */
    public String[] getTitle() {
        return new String[]{"军事", "社会", "股票", "基金", "探索", "手机", "NBA", "英超"};
    }

    /**
     * 根据新闻分类返回对应的fragment
     * 并传递=参数
     */
    public Fragment getFragment(int position) {
        String   title    = getTitle()[position];
        Fragment fragment = MainFragment.newInstance(position, title);
        return fragment;
    }

    static class Adapter extends PagerAdapter {
        List<Fragment>     mFragments = new ArrayList<>();
        List<CharSequence> titles     = new ArrayList<>();
        FragmentManager     mFragmentManager;
        FragmentTransaction mTransaction;
        Fragment            mFragmentItem;

        public Adapter(FragmentManager fragmentManager) {
            mFragmentManager = fragmentManager;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        public void addFragment(Fragment fragment, CharSequence title) {
            mFragments.add(fragment);
            titles.add(title);
        }

        public Fragment getFragmentItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return ((Fragment) object).getView() == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (mTransaction == null) {
                mTransaction = mFragmentManager.beginTransaction();
            }
            int itemId = getItemId(position);
            //判断fragment是否存在
            String   name     = makeFragmentName(container.getId(), itemId);
            Fragment fragment = mFragmentManager.findFragmentByTag(name);
            if (fragment != null) {
                mTransaction.attach(fragment);
            } else {
                fragment = getFragmentItem(position);
                mTransaction.add(container.getId(), fragment, makeFragmentName(container.getId(),
                        itemId));
            }
            //设置不可见
            if (fragment != mFragmentItem) {
                fragment.setMenuVisibility(false);
                fragment.setUserVisibleHint(false);
            }
            return fragment;
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            if (mTransaction != null) {
                mTransaction.commitAllowingStateLoss();
                mTransaction = null;
                mFragmentManager.executePendingTransactions();
            }
        }

        //显示
        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            Fragment fragment = (Fragment) object;
            if (fragment != mFragmentItem) {
                if (mFragmentItem != null) {
                    mFragmentItem.setUserVisibleHint(false);
                    mFragmentItem.setMenuVisibility(false);
                }
                if (fragment != null) {
                    fragment.setMenuVisibility(true);
                    fragment.setUserVisibleHint(true);
                }
                mFragmentItem = fragment;
            }
        }

        //销毁
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (mTransaction == null) {
                mTransaction = mFragmentManager.beginTransaction();
            }
            mTransaction.detach((Fragment) object);
        }

        public int getItemId(int position) {
            return position;
        }

        private static String makeFragmentName(int viewId, long id) {
            return "android:switcher:" + viewId + ":" + id;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
