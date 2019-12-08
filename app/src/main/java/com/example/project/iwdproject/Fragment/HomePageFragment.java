package com.example.project.iwdproject.Fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project.iwdproject.Adapters.HomePageAdapter;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomePageFragment extends BaseFragment {
    public static final String FRAD_TITLE = "frag_title";
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_tilte)
    TextView tvTilte;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.titleBar)
    LinearLayout titleBar;

    Unbinder unbinder;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.secondary_refreshLayout)
    TwinklingRefreshLayout secondaryRefreshLayout;
    @BindView(R.id.ll_home)
    LinearLayout llHome;

    private Context instance;
    public static HomePageFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(FRAD_TITLE, title);
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {
        instance = getActivity();
        tvTilte.setVisibility(View.VISIBLE);
        tvTilte.setText("USpace IWD无人机信息采集租赁");

        SinaRefreshView headerView = new SinaRefreshView(instance);
//        headerView.setVisibility(View.GONE);  //隐藏头刷新布局
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(getResources().getColor(R.color.primary_text));
        final LinearLayoutManager mTwoLinearLayoutManager = new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(mTwoLinearLayoutManager);
        recyclerview.setNestedScrollingEnabled(false);
//        recyclerview.addItemDecoration(new DividerItemDecoration(instance, DividerItemDecoration.VERTICAL));   //添加分割线
        //      openredRecycle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式
        recyclerview.setHasFixedSize(true);
        secondaryRefreshLayout.setHeaderView(headerView);
//        secondaryRefreshLayout.setEnableLoadmore(true);
//        secondaryRefreshLayout.setEnableRefresh(true);
        LoadingView loadingView = new LoadingView(instance);
        loadingView.setVisibility(View.GONE);
        secondaryRefreshLayout.setBottomView(loadingView);
        secondaryRefreshLayout.setOnRefreshListener(mRefreshListenerAdapter);

        HomePageAdapter mHomePageAdapter = new HomePageAdapter(instance,  onRecyclerViewItemClickListener);
        recyclerview.setAdapter(mHomePageAdapter);




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





    @Override
    protected void loadData() {

    }



    /**
     * 下拉和显示更多的数据加载
     */
    private RefreshListenerAdapter mRefreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onRefresh(TwinklingRefreshLayout refreshLayout) {
            super.onRefresh(refreshLayout);
//            pageNum = 1;
//            mAccessToken = SharedPreferencesUtility.getAccessToken(instance);
////            getHomePageListDat(true);  //刷新数据
//            setData(true);
        }

        @Override
        public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
            super.onLoadMore(refreshLayout);
//            pageNum++;
//            getNoticeListData(pageNum,false);   //加载更多
            secondaryRefreshLayout.finishLoadmore();
        }
    };



    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    @OnClick({R.id.iv_right, R.id.tv_right, R.id.rl_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_right:
                break;
            case R.id.tv_right:
                break;
            case R.id.rl_title:
                break;
        }
    }


}
