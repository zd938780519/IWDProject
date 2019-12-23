package com.example.project.iwdproject.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.iwdproject.Beans.BalanceBean;
import com.example.project.iwdproject.Beans.UsdtBalanceBean;
import com.example.project.iwdproject.Beans.ZhuanBean;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.PopupWindow.CommonPopupWindow;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.example.project.iwdproject.View.CustomRadioButton;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

public class TransferNumActivity extends BaseActivity implements CommonPopupWindow.ViewInterface {

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
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_coinname)
    TextView tvCoinname;
    @BindView(R.id.ll_choose)
    RelativeLayout llChoose;
    @BindView(R.id.tv_coinname1)
    TextView tvCoinname1;
    private TransferNumActivity instance;
    private String token;
    private String mAccount;
    private int type = 2;
    private RadioGroup sex_rg;
    private String text = "USTD";

    private CommonPopupWindow popupWindow;
    private BalanceBean.DataBean mBalanceData;
    private UsdtBalanceBean.DataBean mUsdtBalanceData;


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
        tvPhone.setText(mAccount);
        token = SharedPreferencesUtility.getAccessToken(instance);
        getMyUstdbalanceData(token);
    }


    /**
     * 获取我的IWD可用资产
     */
    private void getMybalanceData(final String token) {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getMybalance(token, application)
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
                                tvYue.setText("可用余额：" + mBalanceData.getIWD().getNum());
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
     * 获取我的USTD可用资产
     */
    private void getMyUstdbalanceData(final String token) {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getMyUstdbalance(token, application)
                .compose(this.<UsdtBalanceBean>bindToLifecycle())
                .compose(SchedulerTransformer.<UsdtBalanceBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<UsdtBalanceBean>() {
                    @Override
                    protected void onSuccess(UsdtBalanceBean mUsdtBalanceBean) {
                        if (mUsdtBalanceBean != null) {
                            if (mUsdtBalanceBean.getCode() == 10086) {
                                Toast.makeText(instance, mUsdtBalanceBean.getMessage(), Toast.LENGTH_SHORT).show();
                                mUsdtBalanceData = mUsdtBalanceBean.getData();
                                tvYue.setText("可用余额：" + mUsdtBalanceData.getUSDT().getNum());
                            } else {

                                Toast.makeText(instance, mUsdtBalanceBean.getMessage(), Toast.LENGTH_SHORT).show();
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
     * 手机号转账
     */
    private void getTransferData(final String token, String num) {
//        Log.e("TAG","token====="+token);
        Log.e("TAG", "num111=====" + num);

        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getTransfer(type, num, mAccount, token, application)
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

                                ToastLong(instance, "手机号转账");


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


    /**
     * 邮箱号转账
     */
    private void getEmailTransferData(final String token, String num) {
//        Log.e("TAG","token====="+token);
        Log.e("TAG", "num111=====" + num);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getEmailTransfer(type, num, mAccount, token, application)
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

                                ToastLong(instance, "邮箱号转账");


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

    @OnClick({R.id.iv_left, R.id.tv_left, R.id.rl_back, R.id.im_zhuanzhang, R.id.ll_choose})
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


                if (!mLeasenum.equals("")) {
                    //【全为数字】返回true
                    Boolean result6 = mAccount.matches("[0-9]+");
                    if (result6) {
                        getTransferData(token, mLeasenum);

                    } else {
                        getEmailTransferData(token, mLeasenum);
                    }
//
                } else {
                    ToastLong(instance, "数量不能为空！");
                }
                break;
            case R.id.ll_choose:
                showDifficultDownPop(llChoose);
                break;
        }
    }


    /**
     * 选择币种
     *
     * @param view
     */
    private void showDifficultDownPop(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.choose_coin)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setViewOnclickListener(instance)
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(view);
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        //获得PopupWindow布局里的View
        switch (layoutResId) {
            case R.layout.choose_coin:
                sex_rg = view.findViewById(R.id.sex_rg);
                CustomRadioButton man_rb = view.findViewById(R.id.man_rb);
                CustomRadioButton woman_rb = view.findViewById(R.id.woman_rb);
                if (type == 2){
                    man_rb.setChecked(true);
                    woman_rb.setChecked(false);
                }else if (type == 1){
                    man_rb.setChecked(false);
                    woman_rb.setChecked(true);
                }
                sex_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.man_rb:
                                text = "USDT";
                                type = 2;
                                tvCoinname.setText(text);
                                tvCoinname1.setText("转账数量("+text+")");
                                getMyUstdbalanceData(token);
                                break;
                            case R.id.woman_rb:
                                text = "IWD";
                                type = 1;
                                tvCoinname.setText(text);
                                tvCoinname1.setText("转账数量("+text+")");
                                getMybalanceData(token);
                                break;
                        }
//                        CustomRadioButton rb = findViewById(view.checkedId);
//
                    }
                });

                break;
        }
    }
}
