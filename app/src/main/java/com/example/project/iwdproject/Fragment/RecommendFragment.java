package com.example.project.iwdproject.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.iwdproject.Adapters.HomePageAdapter;
import com.example.project.iwdproject.Adapters.RecommendAdapter;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.Utils.RecycleViewDivider;
import com.mchsdk.paysdk.mylibrary.XcRecyclerView.XCRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecommendFragment extends BaseFragment {


    @BindView(R.id.recycle_commend)
    XCRecyclerView recycleCommend;
    Unbinder unbinder;
    private Context instance;
    private View mFeaderView;

    public static RecommendFragment newInstance(int position) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle b = new Bundle();
        b.putInt("position", position);
        fragment.setArguments(b);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        instance = getActivity();
        final LinearLayoutManager mTwoLinearLayoutManager = new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        recycleCommend.setLayoutManager(mTwoLinearLayoutManager);
        recycleCommend.setNestedScrollingEnabled(false);
//        recyclerview.addItemDecoration(new DividerItemDecoration(instance, DividerItemDecoration.VERTICAL));   //添加分割线
//        recycleCommend.addItemDecoration(new RecycleViewDivider(instance, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式
        recycleCommend.setHasFixedSize(true);
        mFeaderView = LayoutInflater.from(instance).inflate(R.layout.fead_recommend_layout, recycleCommend, false);



        initFeaderView(mFeaderView);

        recycleCommend.addFooterView(mFeaderView);
        RecommendAdapter mRecommendAdapter = new RecommendAdapter(instance,  onRecyclerViewItemClickListener);
        recycleCommend.setAdapter(mRecommendAdapter);

    }





    private OnRecyclerViewItemDeClickListener onRecyclerViewItemClickListener = new OnRecyclerViewItemDeClickListener() {


        @Override
        public void onRecyclerViewItemClicked(int position, RecyclerView.ViewHolder viewHolder) {

        }

        @Override
        public void onRecyclerViewItemLongClicked(int position, RecyclerView.ViewHolder viewHolder) {

        }

        @Override
        public void onRecyclerViewItemClicked() {

        }


    };






    /**
     * fooderView 初始化
     *
     * @param mFeaderView
     */
    private void initFeaderView(View mFeaderView) {

    }


    @Override
    protected void loadData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
