package com.example.project.iwdproject.Activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.iwdproject.Beans.ActiveUsdtBean;
import com.example.project.iwdproject.Beans.LeaseBean;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.EyesUtils;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import java.sql.Date;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

public class AvailableLeaseActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_allcoin)
    TextView tvAllcoin;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.user_agreement)
    TextView userAgreement;
    @BindView(R.id.ll_lease)
    LinearLayout llLease;
    @BindView(R.id.et_leasenum)
    EditText etLeasenum;
    @BindView(R.id.tv_usercoin)
    TextView tvUsercoin;
    @BindView(R.id.tv_leasetime)
    TextView tvLeasetime;
    private AvailableLeaseActivity instance;
    private String token;
    private int coinId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availablelease);
        ButterKnife.bind(this);
        instance = this;
        EyesUtils.setImmersionStateMode(instance);  //实现沉浸
        addActivity(instance);
        initView();
    }



    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("租赁");
        coinId = getIntent().getIntExtra("coinId", 2);
        token = SharedPreferencesUtility.getAccessToken(instance);
        String time= getDataTime();
        tvLeasetime.setText("产生租赁时间："+time);
        getMybalanceData(token);
    }


    /**
     * 获取我的可用资产
     */
    private void getMybalanceData(final String token) {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getActiveUsdt(token, application)
                .compose(this.<ActiveUsdtBean>bindToLifecycle())
                .compose(SchedulerTransformer.<ActiveUsdtBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<ActiveUsdtBean>() {
                    @Override
                    protected void onSuccess(ActiveUsdtBean mActiveUsdtBean) {
                        if (mActiveUsdtBean != null) {
                            if (mActiveUsdtBean.getCode() == 10086) {
                                Toast.makeText(instance, mActiveUsdtBean.getMessage(), Toast.LENGTH_SHORT).show();

                                tvUsercoin.setText(mActiveUsdtBean.getData().getUsdt() + "");
                            } else {

                                Toast.makeText(instance, mActiveUsdtBean.getMessage(), Toast.LENGTH_SHORT).show();
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



    private String getDataTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
//        time1.setText("Date获取当前日期时间"+simpleDateFormat.format(date));
        return simpleDateFormat.format(date);

    }


    /**
     * 租赁
     */
    private void getMybalanceTwoData(String num) {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getLease(num, coinId, token, application)
                .compose(this.<LeaseBean>bindToLifecycle())
                .compose(SchedulerTransformer.<LeaseBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<LeaseBean>() {
                    @Override
                    protected void onSuccess(LeaseBean mLeaseBean) {
                        if (mLeaseBean != null) {
                            if (mLeaseBean.getCode() == 10086) {
                                Toast.makeText(instance, mLeaseBean.getMessage(), Toast.LENGTH_SHORT).show();
                                finishActivity(instance);
//                                mBalanceData = mBalanceTwoBean.getData();

                            } else {

                                Toast.makeText(instance, mLeaseBean.getMessage(), Toast.LENGTH_SHORT).show();
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


    @OnClick({R.id.iv_left, R.id.tv_left, R.id.rl_back, R.id.ll_lease, R.id.user_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.tv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.ll_lease:   //租赁
                String mLeasenum = etLeasenum.getText().toString().trim();
                if (check.isChecked() == true) {
                    if (!mLeasenum.equals("")) {
                        getMybalanceTwoData(mLeasenum);
                    }
                } else {
                    ToastLong(instance, "请确定已阅读协议！");
                }

                break;
            case R.id.user_agreement:
                Intent AgreementIntent = new Intent(instance, AgreementActivity.class);
                startActivity(AgreementIntent);
                break;
        }
    }
}
