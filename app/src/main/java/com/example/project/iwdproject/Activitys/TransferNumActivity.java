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

import com.example.project.iwdproject.Beans.ActiveUsdtBean;
import com.example.project.iwdproject.Beans.ZhuanBean;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

public class TransferNumActivity extends BaseActivity {

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
    @BindView(R.id.nothead_iamge)
    CircularImageView notheadIamge;
    @BindView(R.id.et_leasenum)
    EditText etLeasenum;
    @BindView(R.id.tv_allcoin)
    TextView tvAllcoin;
    @BindView(R.id.et_detail)
    EditText etDetail;
    @BindView(R.id.rl_context)
    RelativeLayout rlContext;
    @BindView(R.id.im_zhuanzhang)
    ImageView imZhuanzhang;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    private TransferNumActivity instance;
    private String token;
    private String mAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfernum);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        initView();
    }


    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("转账");
        mAccount = getIntent().getStringExtra("mAccount");
        token = SharedPreferencesUtility.getAccessToken(instance);
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
                                tvYue.setText("余额："+mActiveUsdtBean.getData().getUsdt());
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







    /**
     * 转账
     */
    private void getTransferData(final String token,String num) {
//        Log.e("TAG","token====="+token);
        Log.e("TAG","num111====="+num);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getTransfer(1,num,mAccount,token, application)
                .compose(this.<ZhuanBean>bindToLifecycle())
                .compose(SchedulerTransformer.<ZhuanBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<ZhuanBean>() {
                    @Override
                    protected void onSuccess(ZhuanBean mZhuanBean) {
                        if (mZhuanBean != null) {
                            if (mZhuanBean.getCode() == 10086) {
                                Toast.makeText(instance, mZhuanBean.getMessage(), Toast.LENGTH_SHORT).show();
                                finishActivity(instance);
                            } else {

                                Toast.makeText(instance, mZhuanBean.getMessage(), Toast.LENGTH_SHORT).show();
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

    @OnClick({R.id.iv_left, R.id.tv_left, R.id.rl_back,R.id.im_zhuanzhang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.tv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.im_zhuanzhang:
                String mLeasenum = etLeasenum.getText().toString().trim();

                if (!mLeasenum.equals("")){
                    getTransferData(token,mLeasenum);
                }else {
                    ToastLong(instance,"数量不能为空！");
                }
                break;
        }
    }
}
