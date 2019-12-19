package com.example.project.iwdproject.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project.iwdproject.Adapters.HomePageAdapter;
import com.example.project.iwdproject.Adapters.LeaseAdapter;
import com.example.project.iwdproject.Beans.BalanceBean;
import com.example.project.iwdproject.Beans.InvitationCodeBean;
import com.example.project.iwdproject.Beans.MyBalanceListBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Action;

import static com.example.project.iwdproject.Fragment.HomePageFragment.FRAD_TITLE;

public class LeaseFragment extends BaseFragment {

    @BindView(R.id.recycle_lease)
    RecyclerView recycleLease;
    Unbinder unbinder;
    private Context instance;
    private BalanceBean.DataBean mBalanceData;
    private List<MyBalanceListBean.DataBean.ListBean> mMyBalanceListData;

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
        instance  = getActivity();
        String token  = SharedPreferencesUtility.getAccessToken(instance);
        getMybalanceData(token);
        final LinearLayoutManager mTwoLinearLayoutManager = new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        recycleLease.setLayoutManager(mTwoLinearLayoutManager);
        recycleLease.setNestedScrollingEnabled(false);
//        recyclerview.addItemDecoration(new DividerItemDecoration(instance, DividerItemDecoration.VERTICAL));   //添加分割线
        //      openredRecycle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式
        recycleLease.setHasFixedSize(true);


    }


    /**
     * 获取我的总资产
     */
    private void getMybalanceData(final String token) {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getMybalance(token,application)
                .compose(this.<BalanceBean>bindToLifecycle())
                .compose(SchedulerTransformer.<BalanceBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<BalanceBean>() {
                    @Override
                    protected void onSuccess(BalanceBean mBalanceBean) {
                        if (mBalanceBean != null) {
                            if (mBalanceBean.getCode() == 10086) {
                                Toast.makeText(instance, mBalanceBean.getMessage(), Toast.LENGTH_SHORT).show();
                                mBalanceData = mBalanceBean.getData();
//                                ToastShort(instance,mInvitationCodeBean.getMessage());

                                getBalanceListData(token);
//                                LeaseAdapter mLeaseAdapter = new LeaseAdapter(instance, mBalanceData,mMyBalanceListData, onRecyclerViewItemClickListener);
//                                recycleLease.setAdapter(mLeaseAdapter);
//                                update3(apkUrl);
                            } else {

                                Toast.makeText(instance, mBalanceBean.getMessage(), Toast.LENGTH_SHORT).show();
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









    /**
     * 获取我资产列表
     */
    private void getBalanceListData(String token) {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getBalanceList(token,application)
                .compose(this.<MyBalanceListBean>bindToLifecycle())
                .compose(SchedulerTransformer.<MyBalanceListBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

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
                                mMyBalanceListData = mMyBalanceListBean.getData().getList();
                                LeaseAdapter mLeaseAdapter = new LeaseAdapter(instance, mBalanceData,mMyBalanceListData, onRecyclerViewItemClickListener);
                                recycleLease.setAdapter(mLeaseAdapter);
//                                update3(apkUrl);
                            } else {

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

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
