package edu.hsl.myappnewsday.ui.frgment;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.bean.NewsBean;
import edu.hsl.myappnewsday.ui.activity.MainActivity;
import edu.hsl.myappnewsday.ui.adapter.NewsAdapter;

;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";

    MainActivity mMainActivity;
    int newsId = 0;//新闻分类id
    RecyclerView mRecyclerView;
    NewsAdapter  adapter;
//    GestureDetector mGestureDetector;
//    int             width;
//    LeftFragment    fragmentLeft;
//    RightFragment   fragmentRight;
//    View         mView;
//    ImageView    iv_drop;
//    TextView     tv_drop_text;
//    TextView     tv_drop_time;
//    float x1 = 0, x2 = 0;//点击屏幕按下与弹起点的坐标
//    float y1 = 0, y2 = 0;
//    float praentX = 0;//获得子布局相对于屏幕的偏移量

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        mMainActivity = (MainActivity) getActivity();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_news_item);
//        mGestureDetector = new GestureDetector(getActivity(), new MyOnGestureListener());
//        DisplayMetrics metrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        width = metrics.widthPixels;
        newsId = mMainActivity.newsId;
        initData(mMainActivity.mUtil.getUri("1", "1", "1", "1", "20150520", "20"));
        initView();
//        BaseActivity.TouchListener listener = new BaseActivity.TouchListener() {
//            @Override
//            public void onTouchEvent(MotionEvent event) {
//
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        praentX = mMainActivity.fl_main.getLeft();//获得子布局相对于屏幕的偏移量
//                        x1 = event.getX();
//                        Log.d(TAG, "onTouchEvent: 按下" + x1);
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        x2 = event.getX();
//                        Log.d(TAG, "onTouchEvent: 弹起" + x2);
//                        if (x1 - x2 > 300 && x1 - praentX > width - 200) {
//                            setRight();
//                        }
//                        if (x2 - x1 > 300 && x1 - praentX > width - 200) {
//                            initLocation();
//                        }
//                        if (x2 - x1 > 300 && x1 - praentX < 200) {
//                            setLeft();
//                        }
//                        if (x1 - x2 > 300 && x1 - praentX < 200) {
//                            initLocation();
//                        }
//                        break;
//                }
//
//            }
//
//            @Override
//            public boolean onInterceptTuochEvent(MotionEvent event) {
//                boolean result = true;
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        x1 = event.getX();
//                        y1 = event.getY();
//                        Log.d(TAG, "onTouchEvent: 1按下" + y1);
//                        Log.d(TAG, "onTouchEvent: 1x按下" + x1);
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        break;
//                    case MotionEvent.ACTION_UP:
//
//                        x2 = event.getX();
//                        y2 = event.getY();
//                        Log.d(TAG, "onTouchEvent: 1弹起" + y2);
//                        Log.d(TAG, "onTouchEvent: 1x弹起" + x2);
//                        if (Math.abs(x1 - x2) > Math.abs(y1 - y2)) {
//                            result = false;
//                        }
//                        break;
//                }
//
//                return result;
//            }
//        };
//        mMainActivity.registerTouchListener(listener);
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return mGestureDetector.onTouchEvent(event);
//            }
//        });
        return view;
    }

    @TargetApi(23)
    public void initView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getDrawable(R
                .mipmap.divider)));
//        AnimRFLinearLayoutManager layout = new AnimRFLinearLayoutManager(getActivity());
//        layout.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(layout);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), layout
//                .getOrientation(), true));
        adapter = new NewsAdapter(getActivity());
        mRecyclerView.setAdapter(adapter);

    }

    @TargetApi(23)
    @Override
    public void onResume() {

        adapter.upData();
//        mView = getActivity().getLayoutInflater().inflate(R.layout.layout_header_updata, null);
//        iv_drop = (ImageView) mView.findViewById(R.id.iv_drop);
//        tv_drop_text = (TextView) mView.findViewById(R.id.tv_drop_text);
//        tv_drop_time = (TextView) mView.findViewById(R.id.tv_drop_time);
//        mRecyclerView.addView(mView);
//        adapter.upData();adapter.upData();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        super.onResume();
    }

    public void initData(String uri) {

        new AsyncTask<String, Void, String>() {


            @Override
            protected String doInBackground(String... params) {
                return mMainActivity.mUtil.getData(params[0]);

            }

            @Override
            protected void onPostExecute(String s) {
                NewsBean bean = mMainActivity.mJsonUtil.getNewsBean(s);
                adapter.add(bean.getData());
                adapter.upData();
                super.onPostExecute(s);
            }
        }.execute(uri);
    }

//    private void setLeft() {
//        FragmentManager     fm          = getFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        fragmentLeft = new LeftFragment();
//        transaction.replace(R.id.fl_left, fragmentLeft);
////        transaction.addToBackStack("");
//        transaction.commit();
//        mMainActivity.setLeft();
//    }

//    private void setRight() {
//        FragmentManager     fm          = getFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        fragmentRight = new RightFragment();
//        transaction.replace(R.id.fl_right, fragmentRight);
////        transaction.addToBackStack("");
//        transaction.commit();
//        mMainActivity.setRight();
//    }
//
//    public void initLocation() {
//        FragmentManager     fm          = getFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        if (fragmentLeft != null) {
//            transaction.remove(fragmentLeft);
//        }
//        if (fragmentRight != null) {
//            transaction.remove(fragmentRight);
//        }
//        transaction.commit();
//        mMainActivity.initLocation();
//    }

//    class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            Log.d(TAG, "onFling: 走没走走没走走没走走没走走没走走没走走没走");
//            if (e1.getX() - e2.getX() > 100 && Math.abs(velocityX) > 200 && e1.getX() > width -
//                    150) {
//                setRight();//向左滑动
//            }
//            if (e2.getX() - e1.getX() > 100 && Math.abs(velocityX) > 200 && e1.getX() > width -
//                    300) {
//                initLocation();//滑动返回
//            }
//            if (e2.getX() - e1.getX() > 100 && Math.abs(velocityX) > 200 && e1.getX() < 150) {
//                setLeft();//向右滑动
//            }
//            if (e1.getX() - e2.getX() > 100 && Math.abs(velocityX) > 200 && e1.getX() < 300) {
//                initLocation();//滑动返回
//            }
//            return true;
//        }
//
//        @Override
//        public boolean onDown(MotionEvent e) {
//            return true;
//        }
//    }
}
