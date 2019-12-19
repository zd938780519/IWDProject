package com.example.project.iwdproject.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project.iwdproject.Beans.EtcInfoBean;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.PackagUtils;
import com.example.project.iwdproject.Utils.PopupWindow.CommonPopupWindow;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

public class PunchActivity extends BaseActivity implements CommonPopupWindow.ViewInterface {   //冲币
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
    @BindView(R.id.ll_copy)
    LinearLayout llCopy;
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;
    @BindView(R.id.tv_coinname)
    TextView tvCoinname;
    @BindView(R.id.iv_ercode)
    ImageView ivErcode;
    @BindView(R.id.tv_adress)
    TextView tvAdress;
    private PunchActivity instance;
    private RadioGroup sex_rg;
    private CommonPopupWindow popupWindow;
    private String token;
    private int type = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        initView();
    }

    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("充值");
        token = SharedPreferencesUtility.getAccessToken(instance);
        getEtcInfoData(token);
    }


    @OnClick({R.id.iv_left, R.id.tv_left, R.id.rl_back, R.id.ll_choose,R.id.ll_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.tv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.ll_choose:   //选择币种
                showDifficultDownPop(llChoose);
                break;
            case R.id.ll_copy:
                String mAdress = tvAdress.getText().toString().trim();
                PackagUtils.copy(instance,mAdress);
                break;
        }
    }


    /**
     * 获取充值页面信息
     */
    private void getEtcInfoData(String token) {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getEtcInfo(token, application)
                .compose(this.<EtcInfoBean>bindToLifecycle())
                .compose(SchedulerTransformer.<EtcInfoBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<EtcInfoBean>() {
                    @Override
                    protected void onSuccess(EtcInfoBean mEtcInfoBean) {
                        if (mEtcInfoBean != null) {
                            if (mEtcInfoBean.getCode() == 10086) {
                                Toast.makeText(instance, mEtcInfoBean.getMessage(), Toast.LENGTH_SHORT).show();
//
                                Glide.with(instance).load(mEtcInfoBean.getData().getQrcode()).placeholder(R.mipmap.img_rwm_1).into(ivErcode);
                                tvAdress.setText(mEtcInfoBean.getData().getAddress());
//                                ToastShort(instance,mInvitationCodeBean.getMessage());

//                                update3(apkUrl);
                            } else {

                                Toast.makeText(instance, mEtcInfoBean.getMessage(), Toast.LENGTH_SHORT).show();
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
                sex_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.man_rb:
                                tvCoinname.setText("USDT");
                                type = 2;

                                break;
                            case R.id.woman_rb:
                                tvCoinname.setText("IWD");
                                type = 1;

                                break;
                        }
//                        CustomRadioButton rb = findViewById(view.checkedId);
//
                    }
                });
//                tv_usdt  = view.findViewById(R.id.tv_usdt);
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
