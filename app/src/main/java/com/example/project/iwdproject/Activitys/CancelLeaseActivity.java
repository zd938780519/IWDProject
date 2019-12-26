package com.example.project.iwdproject.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.iwdproject.Beans.LeaseDetailBean;
import com.example.project.iwdproject.Beans.LeaseStopBean;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.EyesUtils;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

public class CancelLeaseActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.et_coinnum)
    TextView etCoinnum;
    @BindView(R.id.ll_clease)
    LinearLayout llClease;
    @BindView(R.id.tv_allnum)
    TextView tvAllnum;
    @BindView(R.id.tv_baifenlv)
    TextView tvBaifenlv;
    private CancelLeaseActivity instance;
    private String token;
    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancellease);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        EyesUtils.setImmersionStateMode(instance);  //实现沉浸
        initView();
    }

    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("取消租赁");
        id = getIntent().getIntExtra("id",0);
        token = SharedPreferencesUtility.getAccessToken(instance);

        getLeaseDetailData();
    }


    /**
     * 获取租赁详情
     */
    private void getLeaseDetailData() {
        Log.e("TAG", "token=====" + token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getLeaseDetail(id, token, application)
                .compose(this.<LeaseDetailBean>bindToLifecycle())
                .compose(SchedulerTransformer.<LeaseDetailBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<LeaseDetailBean>() {
                    @Override
                    protected void onSuccess(LeaseDetailBean mLeaseDetailBean) {
                        if (mLeaseDetailBean != null) {
                            if (mLeaseDetailBean.getCode() == 10086) {
                                ToastShort(instance, mLeaseDetailBean.getMessage());
                                tvAllnum.setText(mLeaseDetailBean.getData().getTotal());
                                tvTime.setText(mLeaseDetailBean.getData().getCreated_at());
                                tvDay.setText(mLeaseDetailBean.getData().getDays() + "");
                                tvBaifenlv.setText("USDT租赁解约需扣"+mLeaseDetailBean.getData().getFee()+"的手续费");
                                etCoinnum.setText(mLeaseDetailBean.getData().getTotal());
//                                id = mLeaseDetailBean.getData().getId();

                            } else {
                                ToastShort(instance, mLeaseDetailBean.getMessage());
                            }

                        }
                    }

                    @Override
                    protected void onFailed(HttpResponseException responseException) {
                        super.onFailed(responseException);
//                        ToastShort.showShortToast("网络错误！");
                        ToastShort(instance, "error code : " + responseException.getStatus());
//                        ToastShort(instance, "网络有误！");
                    }
                });
    }








    /**
     * 取消租赁
     */
    private void getLeaseStopData() {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getLeaseStop(id, token, application)
                .compose(this.<LeaseStopBean>bindToLifecycle())
                .compose(SchedulerTransformer.<LeaseStopBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<LeaseStopBean>() {
                    @Override
                    protected void onSuccess(LeaseStopBean mLeaseStopBean) {
                        if (mLeaseStopBean != null) {
                            if (mLeaseStopBean.getCode() == 10086) {
                                Toast.makeText(instance, mLeaseStopBean.getMessage(), Toast.LENGTH_SHORT).show();
//                                mBalanceData = mBalanceTwoBean.getData();
                                finishActivity(instance);




                            } else {

                                Toast.makeText(instance, mLeaseStopBean.getMessage(), Toast.LENGTH_SHORT).show();
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


    @OnClick({R.id.iv_left, R.id.tv_left, R.id.rl_back,R.id.ll_clease})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.tv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.ll_clease:
                getLeaseStopData();
                break;
        }
    }
}
