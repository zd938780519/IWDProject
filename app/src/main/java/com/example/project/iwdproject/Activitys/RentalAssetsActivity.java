package com.example.project.iwdproject.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.iwdproject.Beans.BalanceBean;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.EyesUtils;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.example.project.iwdproject.View.NoticeView;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

public class RentalAssetsActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.net_text)
    NoticeView netText;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.chongbi)
    LinearLayout chongbi;
    @BindView(R.id.tibi)
    LinearLayout tibi;
    @BindView(R.id.ll_shanchong)
    LinearLayout llShanchong;
    @BindView(R.id.zhuanzhang)
    LinearLayout zhuanzhang;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_tibi)
    TextView tvTibi;
    private RentalAssetsActivity instance;
    private BalanceBean.DataBean mBalanceData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentalassets);
        ButterKnife.bind(this);
        instance = this;
        EyesUtils.setImmersionStateMode(instance);  //实现沉浸
        addActivity(instance);
        initView();
    }


    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("IDW资产");
        String token  = SharedPreferencesUtility.getAccessToken(instance);
        getMybalanceData(token);

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
                                tvPrice.setText("≈"+mBalanceData.getIWD().getTotal_price());
                                tvNumber.setText(mBalanceData.getIWD().getTotal());
                                tvYue.setText(mBalanceData.getIWD().getNum());
                                tvTibi.setText(mBalanceData.getIWD().getWithdrawal());

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




    @OnClick({R.id.iv_left, R.id.rl_back, R.id.net_text,R.id.chongbi,R.id.tibi
            ,R.id.ll_shanchong,R.id.zhuanzhang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.net_text:
                break;
            case R.id.chongbi:   //冲币
                Intent PunchIntent = new Intent(instance,PunchActivity.class);
                startActivity(PunchIntent);
                break;
            case R.id.tibi:  //提币
                Intent coinIntent = new Intent(instance,CoinsActivity.class);
                startActivity(coinIntent);
                break;
            case R.id.ll_shanchong:  //闪兑
                Intent FlashIntent = new Intent(instance,FlashActivity.class);
                startActivity(FlashIntent);
                break;
            case R.id.zhuanzhang:   //转账
                Intent TransferIntent = new Intent(instance,TransferActivity.class);
                startActivity(TransferIntent);
                break;
        }
    }
}
