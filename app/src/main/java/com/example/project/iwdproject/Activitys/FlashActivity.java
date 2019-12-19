package com.example.project.iwdproject.Activitys;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project.iwdproject.Beans.ExchangeBean;
import com.example.project.iwdproject.Beans.FlashBean;
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

public class FlashActivity extends BaseActivity {
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
    @BindView(R.id.et_iwdnum)
    EditText etIwdnum;
    @BindView(R.id.et_USDTnum)
    TextView etUSDTnum;
    @BindView(R.id.iv_btn)
    ImageView ivBtn;
    private FlashActivity instance;
    private String token;
    private  int type = 1;
    private boolean flag=false;//标记edittext不会死循环

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        initView();
    }


    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("闪兑");

        token = SharedPreferencesUtility.getAccessToken(instance);


        etIwdnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Log.i(iTAG, "beforeTextChanged: 输入过程中执行该方法");


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.i(TAG, "onTextChanged: 输入前确认执行该方法");

            }

            @Override
            public void afterTextChanged(Editable s) {
//                Log.i(TAG, "afterTextChanged: 输入结束执行该方法");
                type = 1;
                getWithdrawalData(type,s.toString().trim(),token);
            }
        });




//
//        etUSDTnum.setOnFocusChangeListener(new android.view.View.
//                OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    // 此处为得到焦点时的处理内容
//                    etUSDTnum.getText().clear();
//                    etUSDTnum.addTextChangedListener(new TextWatcher() {
//                        @Override
//                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
////                Log.i(iTAG, "beforeTextChanged: 输入过程中执行该方法");
//
//                        }
//
//                        @Override
//                        public void onTextChanged(CharSequence s, int start, int before, int count) {
////                Log.i(TAG, "onTextChanged: 输入前确认执行该方法");
//
//                        }
//
//                        @Override
//                        public void afterTextChanged(Editable s) {
////                Log.i(TAG, "afterTextChanged: 输入结束执行该方法");
//                            type = 2;
//                            getWithdrawalData(2,s.toString().trim(),token);
//                        }
//                    });
//
//                } else {
//                    // 此处为失去焦点时的处理内容
//                }
//            }
//        });







    }


    /**
     * 兑换计算
     */
    private void getWithdrawalData(final int type, String num, String token) {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getFlash(type, num, token, application)
                .compose(this.<FlashBean>bindToLifecycle())
                .compose(SchedulerTransformer.<FlashBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<FlashBean>() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    protected void onSuccess(FlashBean mFlashBean) {
                        if (mFlashBean != null) {
                            if (mFlashBean.getCode() == 10086) {
                                ToastShort(instance, mFlashBean.getMessage());
//                                if (type ==1){
                                etUSDTnum.setTextColor(Color.WHITE);
                                    etUSDTnum.setText(mFlashBean.getData().getNum());

//                                }else if (type ==2){
//                                    etIwdnum.setText(mFlashBean.getData().getNum());
//                                }

                            } else {
                                ToastShort(instance, mFlashBean.getMessage());
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
     * 确认兑换
     */
    private void getExchangeData( int type, String num, String token) {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getExchange(type, num, token, application)
                .compose(this.<ExchangeBean>bindToLifecycle())
                .compose(SchedulerTransformer.<ExchangeBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<ExchangeBean>() {
                    @Override
                    protected void onSuccess(ExchangeBean mExchangeBean) {
                        if (mExchangeBean != null) {
                            if (mExchangeBean.getCode() == 10086) {
                                ToastShort(instance, mExchangeBean.getMessage());
                                finishActivity(instance);
//                                if (type ==1){
//                                    etUSDTnum.setText(mFlashBean.getData().getNum());
//                                }else if (type ==2){
//                                    etIwdnum.setText(mFlashBean.getData().getNum());
//                                }

                            } else {
                                ToastShort(instance, mExchangeBean.getMessage());
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





    @OnClick({R.id.iv_left, R.id.tv_left, R.id.rl_back,R.id.iv_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.tv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.iv_btn:  //确认
                String mIwdnum = etIwdnum.getText().toString().trim();
//                String mUSDTnum = etUSDTnum.getText().toString().trim();
//                if (type ==1){
                    getExchangeData(type, mIwdnum, token);
//                }else if (type ==2){
//                    getExchangeData(type, mUSDTnum, token);
//                }




                break;
        }
    }
}
