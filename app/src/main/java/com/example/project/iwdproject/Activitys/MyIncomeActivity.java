package com.example.project.iwdproject.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.iwdproject.Adapters.MyIncomeAdapter;
import com.example.project.iwdproject.Beans.MyProfitBean;
import com.example.project.iwdproject.Beans.ProfitLogBean;
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

public class MyIncomeActivity extends BaseActivity {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_peonum)
    TextView tvPeonum;
    @BindView(R.id.iv_back1)
    ImageView ivBack1;
    @BindView(R.id.tv_teamnum)
    TextView tvTeamnum;
//    @BindView(R.id.iv_back2)
//    ImageView ivBack2;
    @BindView(R.id.tv_benefitsnum)
    TextView tvBenefitsnum;
    @BindView(R.id.recycle_myincome)
    RecyclerView recycleMyincome;
    @BindView(R.id.todat_income)
    TextView todatIncome;
    @BindView(R.id.tv_todayusdt)
    TextView tvTodayusdt;
    @BindView(R.id.severincome)
    TextView severincome;
    @BindView(R.id.tv_yueincome)
    TextView tvYueincome;
    @BindView(R.id.rl_tuandui)
    RelativeLayout rlTuandui;
    private MyIncomeActivity instance;
    private MyProfitBean.DataBean mMyProfitData;

    private List<ProfitLogBean.DataBean> mProfitLogData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myincome);
        ButterKnife.bind(this);
        instance = this;
        EyesUtils.setImmersionStateMode(instance);  //实现沉浸
        addActivity(instance);
        initView();
    }

    private void initView() {
        String token = SharedPreferencesUtility.getAccessToken(instance);
        getMyProfitData(token);
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("我的收益");


        final LinearLayoutManager mTwoLinearLayoutManager = new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        recycleMyincome.setLayoutManager(mTwoLinearLayoutManager);
        recycleMyincome.setNestedScrollingEnabled(false);
        recycleMyincome.addItemDecoration(new DividerItemDecoration(instance, DividerItemDecoration.VERTICAL));   //添加分割线
        //      openredRecycle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式


    }


    /**
     * 我的收益记录列表
     */
    private void getBalanceListData(String token) {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getProfitLog(token, application)
                .compose(this.<ProfitLogBean>bindToLifecycle())
                .compose(SchedulerTransformer.<ProfitLogBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<ProfitLogBean>() {
                    @Override
                    protected void onSuccess(ProfitLogBean mProfitLogBean) {
                        if (mProfitLogBean != null) {
                            if (mProfitLogBean.getCode() == 10086) {
                                Toast.makeText(instance, mProfitLogBean.getMessage(), Toast.LENGTH_SHORT).show();
                                mProfitLogData = mProfitLogBean.getData();


                                MyIncomeAdapter mMyIncomeAdapter = new MyIncomeAdapter(instance, mProfitLogData, onRecyclerViewItemClickListener);
                                recycleMyincome.setAdapter(mMyIncomeAdapter);
                            } else {

                                Toast.makeText(instance, mProfitLogBean.getMessage(), Toast.LENGTH_SHORT).show();
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
     * 我的收益
     */
    private void getMyProfitData(final String token) {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getMyProfit(token, application)
                .compose(this.<MyProfitBean>bindToLifecycle())
                .compose(SchedulerTransformer.<MyProfitBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<MyProfitBean>() {
                    @Override
                    protected void onSuccess(MyProfitBean mMyProfitBean) {
                        if (mMyProfitBean != null) {
                            if (mMyProfitBean.getCode() == 10086) {
                                getBalanceListData(token);
//                                Toast.makeText(instance, mMyProfitBean.getMessage(), Toast.LENGTH_SHORT).show();
                                mMyProfitData = mMyProfitBean.getData();
                                todatIncome.setText(mMyProfitData.getTotal() + "");
                                tvTodayusdt.setText("≈" + mMyProfitData.getTotal_usdt() + " USDT");
                                severincome.setText(mMyProfitData.getSeven() + "");
                                tvYueincome.setText(mMyProfitData.getThirty() + "");
                                tvPeonum.setText(mMyProfitData.getDirect());
                                tvTeamnum.setText(mMyProfitData.getTeam());
                                tvBenefitsnum.setText(mMyProfitData.getTeam_profit() + "");

                            } else {

                                Toast.makeText(instance, mMyProfitBean.getMessage(), Toast.LENGTH_SHORT).show();
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


    @OnClick({R.id.iv_left, R.id.rl_back, R.id.iv_back,R.id.rl_tuandui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.iv_back:
                break;
            case R.id.rl_tuandui:    //团队人数
                Intent TeamIntent = new Intent(instance,TeamActivity.class);
                startActivity(TeamIntent);

                break;
        }
    }
}
