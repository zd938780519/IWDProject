package com.example.project.iwdproject.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.iwdproject.Adapters.LeaseTwoAdapter;
import com.example.project.iwdproject.Beans.MyBalanceListBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.EyesUtils;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

public class LeaseActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_leave)
    TextView tvLeave;
    @BindView(R.id.lease_recycle)
    RecyclerView leaseRecycle;
    @BindView(R.id.tv_totlenum)
    TextView tvTotlenum;
    private LeaseActivity instance;
    private List<MyBalanceListBean.DataBean.ListBean> mMyBalanceListData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lease);
        ButterKnife.bind(this);
        instance = this;
        EyesUtils.setImmersionStateMode(instance);  //实现沉浸
        addActivity(instance);
        initView();
    }

    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("租赁计划");
        String token = SharedPreferencesUtility.getAccessToken(instance);
        getBalanceListData(token);

        final LinearLayoutManager mTwoLinearLayoutManager = new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        leaseRecycle.setLayoutManager(mTwoLinearLayoutManager);
        leaseRecycle.setNestedScrollingEnabled(false);
//        recyclerview.addItemDecoration(new DividerItemDecoration(instance, DividerItemDecoration.VERTICAL));   //添加分割线
        //      openredRecycle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式
        leaseRecycle.setHasFixedSize(true);


    }


    /**
     * 获取我资产列表
     */
    private void getBalanceListData(String token) {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getBalanceList(token, application)
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

                                mMyBalanceListData = mMyBalanceListBean.getData().getList();

                                tvTotlenum.setText("≈"+mMyBalanceListBean.getData().getTotal_num());
                                LeaseTwoAdapter mLeaseTwoAdapter = new LeaseTwoAdapter(instance, mMyBalanceListData,onRecyclerViewItemClickListener);
                                leaseRecycle.setAdapter(mLeaseTwoAdapter);
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


    @OnClick({R.id.iv_left, R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
        }
    }
}
