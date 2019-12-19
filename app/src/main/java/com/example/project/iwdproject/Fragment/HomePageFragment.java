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
import android.widget.Toast;

import com.example.project.iwdproject.Adapters.HomePageAdapter;
import com.example.project.iwdproject.Beans.CodeBean;
import com.example.project.iwdproject.Beans.FirstHomePageBean;
import com.example.project.iwdproject.Beans.MarketBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Action;

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
    private FirstHomePageBean.DataBean mFirstHomeData;
    private List<MarketBean.DataBean> mMarketData = new ArrayList<>();

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




//        getFirstHomeData();
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
        getFirstHomeData();
    }

    /**
     * 首页banner  和 滚动通告
     */
    private void getFirstHomeData() {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getFirstHome(application)
                .compose(this.<FirstHomePageBean>bindToLifecycle())
                .compose(SchedulerTransformer.<FirstHomePageBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<FirstHomePageBean>() {
                    @Override
                    protected void onSuccess(FirstHomePageBean mFirstHomePageBean) {
                        if (mFirstHomePageBean != null) {
                            if (mFirstHomePageBean.getCode() == 10086) {
                                Toast.makeText(instance, mFirstHomePageBean.getMessage(), Toast.LENGTH_SHORT).show();

                                mFirstHomeData = mFirstHomePageBean.getData();
                                getMarketData();

                            } else {
                                Toast.makeText(instance, mFirstHomePageBean.getMessage(), Toast.LENGTH_SHORT).show();
//                                ToastShort(instance, mFirstHomePageBean.getMessage());
                            }

                        }
                    }

                    @Override
                    protected void onFailed(HttpResponseException responseException) {
                        super.onFailed(responseException);
//                        ToastShort.showShortToast("网络错误！");
                        Toast.makeText(instance,  "error code : " + responseException.getStatus(), Toast.LENGTH_SHORT).show();
//                        ToastShort(instance, "error code : " + responseException.getStatus());
//                        ToastShort(instance, "网络有误！");
                    }
                });
    }



    /**
     * 首页币行情
     */
    private void getMarketData() {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getMarket(application)
                .compose(this.<MarketBean>bindToLifecycle())
                .compose(SchedulerTransformer.<MarketBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<MarketBean>() {
                    @Override
                    protected void onSuccess(MarketBean mMarketBean) {
                        if (mMarketBean != null) {
                            if (mMarketBean.getCode() == 10086) {
                                mMarketData =  mMarketBean.getData();
                                HomePageAdapter mHomePageAdapter = new HomePageAdapter(instance,mFirstHomeData,mMarketData,  onRecyclerViewItemClickListener);
                                recyclerview.setAdapter(mHomePageAdapter);

                            } else {
                                Toast.makeText(instance, mMarketBean.getMessage(), Toast.LENGTH_SHORT).show();
//                                ToastShort(instance, mFirstHomePageBean.getMessage());
                            }

                        }
                    }

                    @Override
                    protected void onFailed(HttpResponseException responseException) {
                        super.onFailed(responseException);
//                        ToastShort.showShortToast("网络错误！");
                        Toast.makeText(instance,  "error code : " + responseException.getStatus(), Toast.LENGTH_SHORT).show();
//                        ToastShort(instance, "error code : " + responseException.getStatus());
//                        ToastShort(instance, "网络有误！");
                    }
                });
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
