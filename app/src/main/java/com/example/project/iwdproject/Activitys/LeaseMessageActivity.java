package com.example.project.iwdproject.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.iwdproject.Adapters.LeaseMessageAdapter;
import com.example.project.iwdproject.Beans.BalanceTwoBean;
import com.example.project.iwdproject.Beans.LeaseStopBean;
import com.example.project.iwdproject.Beans.MyProfitBeanLogBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.EyesUtils;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

public class LeaseMessageActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_coinname)
    TextView tvCoinname;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.ll_lease)
    LinearLayout llLease;
    @BindView(R.id.lease_messagerecycle)
    RecyclerView leaseMessagerecycle;
    @BindView(R.id.tv_seven)
    TextView tvSeven;
    @BindView(R.id.tv_thirty)
    TextView tvThirty;
    @BindView(R.id.tv_sever)
    TextView tvSever;
    @BindView(R.id.tv_three)
    TextView tvThree;
    private LeaseMessageActivity instance;
    private List<MyProfitBeanLogBean.DataBean> mMyProfitBeanLogData = new ArrayList<>();
    private int coinId;
    private   String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leasemessage);
        ButterKnife.bind(this);
        instance = this;
        EyesUtils.setImmersionStateMode(instance);  //实现沉浸
        addActivity(instance);
        initView();
    }

    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);

        token = SharedPreferencesUtility.getAccessToken(instance);
        coinId = getIntent().getIntExtra("coinId", 1);
        if (coinId == 1) {
            tvLeft.setText("无人机租赁(IWD)");
            tvCoinname.setText("IWD资产");
            tvSever.setText("近七天收益(IWD)");
            tvThree.setText("累计收益(30天/IWD)");
            llLease.setVisibility(View.GONE);
        } else {
            tvLeft.setText("无人机租赁(USDT)");
            tvCoinname.setText("USDT资产");
            tvSever.setText("近七天收益(USDT)");
            tvThree.setText("累计收益(30天/USDT)");
            llLease.setVisibility(View.VISIBLE);
        }
        getMybalanceTwoData(token, coinId);


        final LinearLayoutManager mTwoLinearLayoutManager = new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        leaseMessagerecycle.setLayoutManager(mTwoLinearLayoutManager);
        leaseMessagerecycle.setNestedScrollingEnabled(false);
        leaseMessagerecycle.addItemDecoration(new DividerItemDecoration(instance, DividerItemDecoration.VERTICAL));   //添加分割线
        //      openredRecycle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式
        leaseMessagerecycle.setHasFixedSize(true);

    }


    /**
     * 获取我的租赁资产
     */
    private void getMybalanceTwoData(final String token, final int coinId) {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getBalanceTwo(coinId, token, application)
                .compose(this.<BalanceTwoBean>bindToLifecycle())
                .compose(SchedulerTransformer.<BalanceTwoBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<BalanceTwoBean>() {
                    @Override
                    protected void onSuccess(BalanceTwoBean mBalanceTwoBean) {
                        if (mBalanceTwoBean != null) {
                            if (mBalanceTwoBean.getCode() == 10086) {
                                Toast.makeText(instance, mBalanceTwoBean.getMessage(), Toast.LENGTH_SHORT).show();

                                getMyProfitBeanLogData(token, coinId);
                                tvNum.setText(mBalanceTwoBean.getData().getTotal());
                                tvCoinname.setText(mBalanceTwoBean.getData().getName() + "资产");
                                tvSeven.setText(mBalanceTwoBean.getData().getSeven());
                                tvThirty.setText(mBalanceTwoBean.getData().getThirty());

                            } else {

                                Toast.makeText(instance, mBalanceTwoBean.getMessage(), Toast.LENGTH_SHORT).show();
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
     * 获取我的租赁资产记录
     */
    private void getMyProfitBeanLogData(final String token, int coinId) {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getMyProfitBeanLog(coinId, token, application)
                .compose(this.<MyProfitBeanLogBean>bindToLifecycle())
                .compose(SchedulerTransformer.<MyProfitBeanLogBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<MyProfitBeanLogBean>() {
                    @Override
                    protected void onSuccess(MyProfitBeanLogBean mMyProfitBeanLogBean) {
                        if (mMyProfitBeanLogBean != null) {
                            if (mMyProfitBeanLogBean.getCode() == 10086) {
                                Toast.makeText(instance, mMyProfitBeanLogBean.getMessage(), Toast.LENGTH_SHORT).show();
//                                mBalanceData = mBalanceTwoBean.getData();
                                mMyProfitBeanLogData = mMyProfitBeanLogBean.getData();
                                LeaseMessageAdapter mLeaseMessageAdapter = new LeaseMessageAdapter(instance, mMyProfitBeanLogData, onRecyclerViewItemClickListener);
                                leaseMessagerecycle.setAdapter(mLeaseMessageAdapter);


                            } else {

                                Toast.makeText(instance, mMyProfitBeanLogBean.getMessage(), Toast.LENGTH_SHORT).show();
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
            Intent CancelLeaseIntent = new Intent(instance,CancelLeaseActivity.class);
            CancelLeaseIntent.putExtra("id",mMyProfitBeanLogData.get(position).getId());
            startActivity(CancelLeaseIntent);
//            int type = mMyProfitBeanLogData.get(position).getToken();
//            String amount=   mMyProfitBeanLogData.get(position).getAmount();
//
//            getLeaseStopData(type,amount);

        }

        @Override
        public void onRecyclerViewItemLongClicked(int position, RecyclerView.ViewHolder viewHolder) {

        }

        @Override
        public void onRecyclerViewItemClicked() {

        }


    };

    @Override
    protected void onResume() {
        super.onResume();
        getMybalanceTwoData(token, coinId);

    }

    @OnClick({R.id.iv_left, R.id.rl_back, R.id.ll_lease})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.ll_lease:   //租赁
                Intent AvailableLeaseIntent = new Intent(instance, AvailableLeaseActivity.class);
                AvailableLeaseIntent.putExtra("coinId",coinId);
                startActivity(AvailableLeaseIntent);
//                Intent CoinLeaseIntent = new Intent(instance, CoinLeaseActivity.class);
//                startActivity(CoinLeaseIntent);
                break;
        }
    }
}
