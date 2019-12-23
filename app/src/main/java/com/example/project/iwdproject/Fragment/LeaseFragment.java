package com.example.project.iwdproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project.iwdproject.Activitys.LoginActivity;
import com.example.project.iwdproject.Adapters.LeaseAdapter;
import com.example.project.iwdproject.Beans.BalanceBean;
import com.example.project.iwdproject.Beans.MyBalanceListBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Action;

import static com.example.project.iwdproject.Fragment.HomePageFragment.FRAD_TITLE;

public class LeaseFragment extends BaseFragment {

    @BindView(R.id.recycle_lease)
    RecyclerView recycleLease;
    Unbinder unbinder;
    @BindView(R.id.secondary_refreshLayout)
    TwinklingRefreshLayout secondaryRefreshLayout;
    Unbinder unbinder1;
    private Context instance;
    private BalanceBean.DataBean mBalanceData;
    private MyBalanceListBean.DataBean mMyBalanceListData;
    private String token;

    public static LeaseFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(FRAD_TITLE, title);
        LeaseFragment fragment = new LeaseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lease;
    }

    @Override
    protected void initView() {
        instance = getActivity();
        token = SharedPreferencesUtility.getAccessToken(instance);

        SinaRefreshView headerView = new SinaRefreshView(instance);
//        headerView.setVisibility(View.GONE);  //隐藏头刷新布局
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(getResources().getColor(R.color.primary_text));

        final LinearLayoutManager mTwoLinearLayoutManager = new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        recycleLease.setLayoutManager(mTwoLinearLayoutManager);
        recycleLease.setNestedScrollingEnabled(false);
//        recyclerview.addItemDecoration(new DividerItemDecoration(instance, DividerItemDecoration.VERTICAL));   //添加分割线
        //      openredRecycle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式
        recycleLease.setHasFixedSize(true);
        secondaryRefreshLayout.setHeaderView(headerView);
//        secondaryRefreshLayout.setEnableLoadmore(true);
//        secondaryRefreshLayout.setEnableRefresh(true);
        LoadingView loadingView = new LoadingView(instance);
        loadingView.setVisibility(View.GONE);
        secondaryRefreshLayout.setBottomView(loadingView);
        secondaryRefreshLayout.setOnRefreshListener(mRefreshListenerAdapter);


    }





    /**
     * 下拉和显示更多的数据加载
     */
    private RefreshListenerAdapter mRefreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onRefresh(TwinklingRefreshLayout refreshLayout) {
            super.onRefresh(refreshLayout);
            getBalanceListData(token,true);
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



//    /**
//     * 获取我的总资产
//     */
//    private void getMybalanceData(final String token) {
////        Log.e("TAG","token====="+token);
//        String application = "application/json";
//        RetrofitHttpUtil.getApiService()
//                .getMybalance(token,application)
//                .compose(this.<BalanceBean>bindToLifecycle())
//                .compose(SchedulerTransformer.<BalanceBean>transformer())
//                .doFinally(new Action() {
//                    @Override
//                    public void run() throws Exception {
//
//                    }
//                })
//                .subscribe(new BaseObserver<BalanceBean>() {
//                    @Override
//                    protected void onSuccess(BalanceBean mBalanceBean) {
//                        if (mBalanceBean != null) {
//                            if (mBalanceBean.getCode() == 10086) {
//                                Toast.makeText(instance, mBalanceBean.getMessage(), Toast.LENGTH_SHORT).show();
//                                mBalanceData = mBalanceBean.getData();
////                                ToastShort(instance,mInvitationCodeBean.getMessage());
//
//                                getBalanceListData(token);
////                                LeaseAdapter mLeaseAdapter = new LeaseAdapter(instance, mBalanceData,mMyBalanceListData, onRecyclerViewItemClickListener);
////                                recycleLease.setAdapter(mLeaseAdapter);
////                                update3(apkUrl);
//                            } else {
//
//                                Toast.makeText(instance, mBalanceBean.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    protected void onFailed(HttpResponseException responseException) {
//                        super.onFailed(responseException);
////                        ToastShort.showShortToast("网络错误！");
//                        Toast.makeText(instance, "error code : " + responseException.getStatus(), Toast.LENGTH_SHORT).show();
////                        ToastShort(instance, "网络有误！");
//                    }
//                });
//    }

//
//    @Override
//    public void onResume() {
//        super.onResume();
//        getBalanceListData(token);
//    }

    /**
     * 获取我资产列表
     */
    private void getBalanceListData(String token,final boolean isRefresh) {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getBalanceList(token, application)
                .compose(this.<MyBalanceListBean>bindToLifecycle())
                .compose(SchedulerTransformer.<MyBalanceListBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isRefresh) {
                            secondaryRefreshLayout.finishRefreshing();
                        }
                    }
                })
                .subscribe(new BaseObserver<MyBalanceListBean>() {
                    @Override
                    protected void onSuccess(MyBalanceListBean mMyBalanceListBean) {
                        if (mMyBalanceListBean != null) {
                            if (mMyBalanceListBean.getCode() == 10086) {
                                Toast.makeText(instance, mMyBalanceListBean.getMessage(), Toast.LENGTH_SHORT).show();
//                                mBalanceData = mMyBalanceListBean.getData();
//                                ToastShort(instance,mInvitationCodeBean.getMessage());
                                mMyBalanceListData = mMyBalanceListBean.getData();
                                LeaseAdapter mLeaseAdapter = new LeaseAdapter(instance, mMyBalanceListData, onRecyclerViewItemClickListener);
                                recycleLease.setAdapter(mLeaseAdapter);
//                                update3(apkUrl);
                            }else if (mMyBalanceListBean.getCode() == 13001){
                                Toast.makeText(instance, "您的账号已在另一设备登录，请重新登录！", Toast.LENGTH_SHORT).show();
                                SharedPreferencesUtility.clearAccessToken(instance);
                                SharedPreferencesUtility.clearUserId(instance);
                                SharedPreferencesUtility.clearLevel(instance);
                                SharedPreferencesUtility.clearUserPhone(instance);
                                SharedPreferencesUtility.clearAvatar(instance);
                                Intent loginIntent = new Intent(instance, LoginActivity.class);
                                startActivity(loginIntent);
                            }else {

                                Toast.makeText(instance, mMyBalanceListBean.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    protected void onFailed(HttpResponseException responseException) {
                        super.onFailed(responseException);
//                        ToastShort.showShortToast("网络错误！");
                        Toast.makeText(instance, "error code : " + responseException.getStatus(), Toast.LENGTH_SHORT).show();
//                        ToastShort(instance, "网络有误！");
                    }
                });
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
        getBalanceListData(token,true);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
