package edu.hsl.myappnewsday.ui.frgment;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.bean.NewsBean;
import edu.hsl.myappnewsday.common.util.BitmpCacheUtil;
import edu.hsl.myappnewsday.ui.activity.MainActivity;
import edu.hsl.myappnewsday.ui.activity.WebActivity;
import edu.hsl.myappnewsday.ui.adapter.NewsAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";

    MainActivity mMainActivity;
    int newsId = 0;//新闻分类id
    RecyclerView       mRecyclerView;
    NewsAdapter        adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    int lastVisibleItem = 0;//最后一条显示信息的索引
    LinearLayoutManager layoutManager;
    RequestQueue        mQueue;
    int                 title_id;
    ImageLoader         mLoader;


    //    SwipeToLoadLayout mSwipeToLoadLayout;
//    TextView           headerText;
//    TextView           headerTime;
//    ImageView          iv_header;
//    ProgressBar        pb_header;
//    TextView           tv_footer;
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
    public static MainFragment newInstance(int title) {
        Bundle       args     = new Bundle();
        MainFragment fragment = new MainFragment();
        args.putInt("NEWS_ID", title);
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * 获得新闻类型id
     * RequestQueue对象
     * MainActivity对象
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQueue = Volley.newRequestQueue(getActivity());
        mLoader = new ImageLoader(mQueue, new BitmpCacheUtil());
        mMainActivity = (MainActivity) getActivity();
        this.title_id = getArguments().getInt("NEWS_ID");
        newsId = title_id + 1;//对应的新闻类型ID
        initData(mMainActivity.mUtil.getUri("1", "1", "1", "1", "20150520", "20"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

//        mGestureDetector = new GestureDetector(getActivity(), new MyOnGestureListener());
//        DisplayMetrics metrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        width = metrics.widthPixels;

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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    /**
     * 初始化数据
     * 上拉加载事件
     * item点击事件
     * 根据新闻id 获取参数
     */

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mSwipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.stll_main);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_news_item);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_news);
//        Log.d(TAG, "onViewCreated:新闻id " + newsId);

        initView();
//        mSwipeToLoadLayout.setOnRefreshListener(getRefreshListener());
//        mSwipeToLoadLayout.setOnLoadMoreListener(getLoadListener());
        mRecyclerView.addOnScrollListener(getScrollListener());
        adapter.setOnItemClickListener(getOnItemClickListener());
    }

    /**
     * Item点击事件 跳转
     */
    @NonNull
    private NewsAdapter.OnItemClickListener getOnItemClickListener() {
        return new NewsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Bundle bundle = new Bundle();
//                Log.d(TAG, "OnItemClick: " + adapter.getData().get(position).getLink());
//                bundle.putString("URL", adapter.getData().get(position).getLink());
////                bundle.putString("TITLE", adapter.getData().get(position).getTitle());
////                bundle.putString("ICON", adapter.getData().get(position).getIcon());
////                bundle.putString("SUMMARY", adapter.getData().get(position).getSummary());
                bundle.putParcelable("DATA", adapter.getData().get(position));
                mMainActivity.startActivity(WebActivity.class, bundle);
                Toast.makeText(mMainActivity, ">>>正在打开网页请稍后<<<", Toast.LENGTH_SHORT)
                        .show();
            }
        };
    }


    /**
     * 设置布局方向
     * item 间距
     * 下拉刷新事件
     */


    public void initView() {
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getDrawable(R
                .mipmap.divider)));
//        AnimRFLinearLayoutManager layout = new AnimRFLinearLayoutManager(getActivity());
//        layout.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(layout);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), layout
//                .getOrientation(), true));
//        View headerView = getActivity().getLayoutInflater().inflate(R.layout.layout_header_updata,
//                null);
//        headerText = (TextView) headerView.findViewById(R.id.tv_drop_text);
//        headerTime = (TextView) headerView.findViewById(R.id.tv_drop_time);
//        iv_header = (ImageView) headerView.findViewById(R.id.iv_drop);
//        pb_header = (ProgressBar) headerView.findViewById(R.id.pb_drop);
//
//        View footerView = getActivity().getLayoutInflater().inflate(R.layout.layout_footer_updata,
//                null);
//        tv_footer = (TextView) footerView.findViewById(R.id.tv_load_bottom);
//        mRecyclerView.addHeaderView(headerView);
//        mRecyclerView.setScaleRatio(2.0f);
//        mRecyclerView.setHeaderImage((ImageView) headerView.findViewById(R.id.iv_drop));
//        mRecyclerView.addFootView(footerView);
//        mRecyclerView.setColor(Color.RED, Color.BLUE);
        mSwipeRefreshLayout.setOnRefreshListener(getRefreshListener());
        if (adapter == null)
            adapter = new NewsAdapter(getActivity(), mLoader);
//        if (mBean != null)
//            adapter.add(mBean.getData());
        mRecyclerView.setAdapter(adapter);
        adapter.upData();
    }

    /**
     * 后台回调 刷新数据
     */
    @Override
    public void onResume() {

        adapter.upData();
//        mView = getActivity().getLayoutInflater().inflate(R.layout.layout_header_updata, null);
//        iv_drop = (ImageView) mView.findViewById(R.id.iv_drop);
//        tv_drop_text = (TextView) mView.findViewById(R.id.tv_drop_text);
//        tv_drop_time = (TextView) mView.findViewById(R.id.tv_drop_time);
//        mRecyclerView.addView(mView);
//        adapter.upData();adapter.upData();
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
        super.onResume();
    }

//    @Override
//    public void onPause() {
//        super.onPause();
////        if (mSwipeToLoadLayout.isRefreshing()) {
////            mSwipeToLoadLayout.setRefreshing(false);
////        }
////        if (mSwipeToLoadLayout.isLoadingMore()) {
////            mSwipeToLoadLayout.setLoadingMore(false);
////        }
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mSwipeToLoadLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mSwipeToLoadLayout.setRefreshing(true);
//            }
//        });
    }


    public void initData(String uri) {

        new AsyncTask<String, Void, String>() {

            /**小数据不如volley 方便
             * */
            @Override
            protected String doInBackground(String... params) {
                Log.d(TAG, "onCreateView: 这个是什么鬼" + newsId);
                return mMainActivity.mUtil.getData(params[0]);

            }

            @Override
            protected void onPostExecute(String s) {
                NewsBean mBean = mMainActivity.mJsonUtil.getNewsBean(s);
                adapter.add(mBean.getData());
                adapter.upData();
                super.onPostExecute(s);
            }
        }.execute(uri);
    }

    /**
     * 下拉刷新 需要一个请求地址参数由于数据库原因  只传了一个固定参数
     */
    @NonNull
    private SwipeRefreshLayout.OnRefreshListener getRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新方法
                StringRequest request = new StringRequest(
                        mMainActivity.mUtil.getUri("1", "1", "1", "1", "20150520", "20"),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                /**网络请求的返回值
                                 * 可以根据返回值的返回类型判断添加数据的方法*/
                                NewsBean bean = mMainActivity.mJsonUtil.getNewsBean(response);
                                adapter.addTop(bean.getData());
                                adapter.upData();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
/**如果请求失败则在此处添加读取本地数据的方法*/
                            }
                        }
                );
                mQueue.add(request);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        };
    }
//    @NonNull
//    private OnLoadMoreListener getLoadListener() {
//        return new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//
//            }
//        };
//    }

//    @NonNull
//    private OnRefreshListener getRefreshListener() {
//        return new OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//        };
//    }

    /**
     * 上啦加载 需要一个请求地址的参数
     */
    @NonNull
    private RecyclerView.OnScrollListener getScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter
                        .getItemCount()) {
//                    if (!ViewCompat.canScrollVertically(recyclerView, 0)) {
////                        mSwipeToLoadLayout.setLoadingMore(true);
//                    }
                    adapter.changeMoreStatus(NewsAdapter.LOADING_MORE);
                    StringRequest request = new StringRequest(
                            mMainActivity.mUtil.getUri("1", "1", "1", "1", "20150520", "20"),
                            new Response.Listener<String>() {
                                /**网络请求成功时调用*/
                                @Override
                                public void onResponse(String response) {
                                    NewsBean bean = mMainActivity.mJsonUtil.getNewsBean(response);
                                    adapter.add(bean.getData());
                                    adapter.upData();
                                }
                            },
                            new Response.ErrorListener() {
                                /**网络请求失败时调用*/
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                    mQueue.add(request);
                }

            }

            /**返回最后一项可视item的索引*/
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        };
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
