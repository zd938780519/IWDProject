package com.example.project.iwdproject.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project.iwdproject.Beans.FeesBean;
import com.example.project.iwdproject.Beans.PayPasswordBean;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

public class PaypassActivity extends BaseActivity {
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
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_show)
    ImageView ivShow;
    @BindView(R.id.et_newpaypass)
    EditText etNewpaypass;
    @BindView(R.id.iv_shownew)
    ImageView ivShownew;
    @BindView(R.id.et_nextpaypass)
    EditText etNextpaypass;
    @BindView(R.id.iv_shownext)
    ImageView ivShownext;
    @BindView(R.id.ll_sure)
    LinearLayout llSure;
    private PaypassActivity instance;
    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypass);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        initView();
    }


    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("支付密码");
        token = SharedPreferencesUtility.getAccessToken(instance);
    }




    /**
     * 修改和设置支付密码
     */
    private void getPayPasswordData(String payPassWord,String loginPass) {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getPayPassword(payPassWord, loginPass, token, application)
                .compose(this.<PayPasswordBean>bindToLifecycle())
                .compose(SchedulerTransformer.<PayPasswordBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<PayPasswordBean>() {
                    @Override
                    protected void onSuccess(PayPasswordBean mPayPasswordBean) {
                        if (mPayPasswordBean != null) {
                            if (mPayPasswordBean.getCode() == 10086) {
                                ToastShort(instance, mPayPasswordBean.getMessage());
                                finishActivity(instance);

                            } else {
                                ToastShort(instance, mPayPasswordBean.getMessage());
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




    @OnClick({R.id.iv_left, R.id.rl_back, R.id.tv_tilte,R.id.ll_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.tv_tilte:
                break;
            case R.id.ll_sure:   //修改支付密码
                String massword = etPassword.getText().toString().trim();
                String mpaypass = etNewpaypass.getText().toString().trim();
                String mNextpaypass = etNextpaypass.getText().toString().trim();
                if (!massword.equals("")){
                    if (!mpaypass.equals("") && !mNextpaypass.equals("")){
                        if (mpaypass.equals(mNextpaypass)){
                            getPayPasswordData(mpaypass,massword);
                        }
                    }else {
                        ToastLong(instance,"支付密码不能为空！");
                    }
                }else {
                    ToastLong(instance,"登录密码不能为空！");
                }

                break;
        }
    }
}
