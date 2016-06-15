package edu.hsl.myappnewsday.ui.frgment;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.bean.NewsBean;
import edu.hsl.myappnewsday.common.util.SerializableUtil;
import edu.hsl.myappnewsday.ui.activity.MainActivity;
import edu.hsl.myappnewsday.ui.activity.WebActivity;
import edu.hsl.myappnewsday.ui.adapter.FavoriteAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    LinearLayoutManager mLayoutManager;
    @BindView(R.id.rv_favorite)
    RecyclerView mRvFavorite;
    FavoriteAdapter     mAdapter;
    List<NewsBean.Data> mDatas;
    MainActivity        mMainActivity;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mMainActivity = (MainActivity) getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);
        mDatas = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mRvFavorite.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvFavorite.setLayoutManager(mLayoutManager);
        mRvFavorite.addItemDecoration(new DividerItemDecoration(getActivity().getDrawable(R
                .mipmap.divider)));
        if (mAdapter == null)
            mAdapter = new FavoriteAdapter(getActivity());
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRvFavorite.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        Set<String> favorite = SerializableUtil.getNids(getActivity(), "FAVORITE");
        for (String s : favorite) {
            if (s != null) {
                NewsBean.Data data = SerializableUtil.deSerialization(s);
                mDatas.add(data);
            }
        }
        mAdapter.addData(mDatas);
//        List<String> datas = new ArrayList<>(favorite);
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter
                .OnRecyclerViewItemClickListener() {

            @Override
            public void onItemClick(View view, int i) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("DATA", (Parcelable) mAdapter.getData().get(i));
                mMainActivity.startActivity(WebActivity.class, bundle);
                Toast.makeText(mMainActivity, ">>>正在打开网页请稍后<<<", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

}
