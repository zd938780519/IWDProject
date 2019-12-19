package com.example.project.iwdproject.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project.iwdproject.Beans.InvitationCodeBean;
import com.example.project.iwdproject.Beans.LoginBean;
import com.example.project.iwdproject.MainActivity;
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
import retrofit2.http.Header;
import util.UpdateAppUtils;

public class PleaseCodeActivity extends BaseActivity {
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
    @BindView(R.id.tv_pleasecode)
    TextView tvPleasecode;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_twocode)
    ImageView ivTwocode;
    @BindView(R.id.tv_disser)
    TextView tvDisser;
    @BindView(R.id.tv_link)
    TextView tvLink;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private PleaseCodeActivity instance;
    private String apkUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_please);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        initView();
    }


    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("邀请码");
        String  token = SharedPreferencesUtility.getAccessToken(instance);
        getInvitationCodeData(token);
    }





    /**
     * 获取邀请码
     */
    private void getInvitationCodeData(String token) {
        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getInvitationCode(1,token,application)
                .compose(this.<InvitationCodeBean>bindToLifecycle())
                .compose(SchedulerTransformer.<InvitationCodeBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<InvitationCodeBean>() {
                    @Override
                    protected void onSuccess(InvitationCodeBean mInvitationCodeBean) {
                        if (mInvitationCodeBean != null) {
                            if (mInvitationCodeBean.getCode() == 10086) {
                                ToastShort(instance,mInvitationCodeBean.getMessage());
                                tvPleasecode.setText("邀请码"+mInvitationCodeBean.getData().getNum());
                                tvPhone.setText(mInvitationCodeBean.getData().getPhone()+"的分享");
                                Glide.with(instance).load(mInvitationCodeBean.getData().getImg_url()).placeholder(R.mipmap.img_rwm_1).into(ivTwocode);
                                apkUrl = mInvitationCodeBean.getData().getUrl();
//                                update3(apkUrl);
                            } else {
                                ToastShort(instance, mInvitationCodeBean.getMessage());
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

//
//    //强制更新
//    private void update3(String apkUrl) {
//        UpdateAppUtils.from( instance)
//                .serverVersionCode(2)
//                .serverVersionName("2.0")
//                .apkPath(apkUrl)
//                .updateInfo("1.修复若干bug\n2.美化部分页面\n3.增加微信支付方式")
//                .isForce(true)
//                .update();
//    }



    @OnClick({R.id.iv_left, R.id.rl_back, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.iv_right:
                break;
        }
    }
}
