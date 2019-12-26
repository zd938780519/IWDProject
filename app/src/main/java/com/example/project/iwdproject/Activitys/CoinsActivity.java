package com.example.project.iwdproject.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.iwdproject.Beans.BalanceBean;
import com.example.project.iwdproject.Beans.DrawalBean;
import com.example.project.iwdproject.Beans.FeesBean;
import com.example.project.iwdproject.Beans.UsdtBalanceBean;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.PopupWindow.CommonPopupWindow;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.example.project.iwdproject.View.CustomRadioButton;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

public class CoinsActivity extends BaseActivity implements CommonPopupWindow.ViewInterface {
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
    @BindView(R.id.ll_choose)
    RelativeLayout llChoose;
    @BindView(R.id.all)
    TextView all;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.coinname)
    TextView coinname;
    @BindView(R.id.user_num)
    TextView userNum;
    @BindView(R.id.iv_tibi)
    ImageView ivTibi;
    @BindView(R.id.rt_adress)
    EditText rtAdress;
    @BindView(R.id.et_coinnum)
    EditText etCoinnum;
    @BindView(R.id.tv_shouxufei)
    TextView tvShouxufei;
    @BindView(R.id.tv_arrival)
    TextView tvArrival;
    @BindView(R.id.tv_coinname)
    TextView tvCoinname;
    @BindView(R.id.tv_shouxufeiname)
    TextView tvShouxufeiname;
    private CoinsActivity instance;
    private CommonPopupWindow popupWindow;
    private RadioGroup sex_rg;
    private String text = "USDT";
    private  int type = 2;
    private  String token;
    private BalanceBean.DataBean mBalanceData;
    private UsdtBalanceBean.DataBean mUsdtBalanceData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coins);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        initView();
    }


    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("提币");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("记录");

        token = SharedPreferencesUtility.getAccessToken(instance);
        getMyUstdbalanceData(token);


        etCoinnum.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                getFeesData(token, s.toString().trim());
//                //s:变化后的所有字符
//                Toast.makeText(getContext(), "变化:"+s+";"+start+";"+before+";"+count, Toast.LENGTH_SHORT).show();
//                Log.i("Seachal:","变化:"+s+";"+start+";"+before+";"+count);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
//                //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数
//                Toast.makeText(getContext(), "变化前:"+s+";"+start+";"+count+";"+after, Toast.LENGTH_SHORT).show();
//                Log.i("Seachal:","变化前:"+s+";"+start+";"+count+";"+after);
            }

            @Override
            public void afterTextChanged(Editable s) {
//                //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数
//                Toast.makeText(getContext(), "变化后:"+s+";", Toast.LENGTH_SHORT).show();
//                Log.i("Seachal:","变化后:"+s+";");
            }
        });


    }


    /**
     * 计算手续费
     */
    private void getFeesData(String token, String num) {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getFees(type, num, token, application)
                .compose(this.<FeesBean>bindToLifecycle())
                .compose(SchedulerTransformer.<FeesBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<FeesBean>() {
                    @Override
                    protected void onSuccess(FeesBean mFeesBean) {
                        if (mFeesBean != null) {
                            if (mFeesBean.getCode() == 10086) {
                                ToastShort(instance, mFeesBean.getMessage());
                                tvShouxufei.setText(mFeesBean.getData().getCharge() + "");
                                tvArrival.setText(mFeesBean.getData().getTrue_num() + text);
                            } else {
                                ToastShort(instance, mFeesBean.getMessage());
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
     * 提币接口
     */
    private void getWithdrawalData( String num,String adress,String token) {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getWithdrawal(type, num,adress, token, application)
                .compose(this.<DrawalBean>bindToLifecycle())
                .compose(SchedulerTransformer.<DrawalBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<DrawalBean>() {
                    @Override
                    protected void onSuccess(DrawalBean mDrawalBean) {
                        if (mDrawalBean != null) {
                            if (mDrawalBean.getCode() == 10086) {
                                ToastShort(instance, mDrawalBean.getMessage());
                                finishActivity(instance);
                            } else {
                                ToastShort(instance, mDrawalBean.getMessage());
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
//                                Toast.makeText(instance, mUsdtBalanceBean.getMessage(), Toast.LENGTH_SHORT).show();

                                mUsdtBalanceData = mUsdtBalanceBean.getData();
                                userNum.setText("可用余额"+mUsdtBalanceData.getUSDT().getNum());

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
     * 获取我的IWD总资产
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
//                                Toast.makeText(instance, mBalanceBean.getMessage(), Toast.LENGTH_SHORT).show();
                                mBalanceData = mBalanceBean.getData();
                                userNum.setText("可用余额"+mBalanceData.getIWD().getNum());


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







    @OnClick({R.id.iv_left, R.id.tv_left, R.id.rl_back, R.id.ll_choose,R.id.iv_tibi,R.id.rl_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.tv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.ll_choose:
                showDifficultDownPop(llChoose);
                break;
            case R.id.iv_tibi:   //提币
                String num = etCoinnum.getText().toString().trim();
                String mAdress = rtAdress.getText().toString().trim();
                if (!num.equals("") && !mAdress.equals("")){
                    getWithdrawalData(num,mAdress,token);
                }else {
                    ToastLong(instance,"地址或提币数量不能为空！");
                }

                break;
            case R.id.rl_right:
                Intent TransferRecordIntent = new Intent(instance,TransferRecordActivity.class);
                TransferRecordIntent.putExtra("typecoin","提币记录");
                TransferRecordIntent.putExtra("type",4);
                startActivity(TransferRecordIntent);
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
    public void getChildView(final View view, int layoutResId) {
        //获得PopupWindow布局里的View
        switch (layoutResId) {
            case R.layout.choose_coin:
                sex_rg = view.findViewById(R.id.sex_rg);
                sex_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId){
                            case R.id.man_rb:
                                text = "USDT";
                                type =2;
                                tvCoinname.setText(text);
                                coinname.setText(text);
                                tvShouxufeiname.setText(text);
                                tvArrival.setText("0.00000000 USDT");
                                getMyUstdbalanceData(token);
                                break;
                            case R.id.woman_rb:
                                text = "IWD";
                                type =1;
                                tvCoinname.setText(text);
                                coinname.setText(text);
                                tvShouxufeiname.setText(text);
                                tvArrival.setText("0.00000000 IWD");
                                getMybalanceData(token);
                                break;
                        }
//                        CustomRadioButton rb = findViewById(view.checkedId);
//
                    }
                });
//                tv_iwd = view.findViewById(R.id.tv_iwd);
//                iv_sure = view.findViewById(R.id.iv_sure);
//                iv_sure.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        popupWindow.dismiss();
//                    }
//                });
//
//                tv_usdt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        tv_usdt.setTextColor(getResources().getColor(R.color.color_6362EB));
//                        tv_iwd.setTextColor(getResources().getColor(R.color.white));
//                    }
//                });
//                tv_iwd.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        tv_usdt.setTextColor(getResources().getColor(R.color.white));
//                        tv_iwd.setTextColor(getResources().getColor(R.color.color_6362EB));
//                    }
//                });

                break;
        }
    }


}
