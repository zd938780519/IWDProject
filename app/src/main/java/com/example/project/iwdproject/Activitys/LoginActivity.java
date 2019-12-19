package com.example.project.iwdproject.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.iwdproject.Beans.EmailBean;
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

import static com.example.project.iwdproject.Utils.ValidationUntils.checkRegister;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_show)
    ImageView ivShow;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.user_agreement)
    TextView userAgreement;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.tv_forgetpass)
    TextView tvForgetpass;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_emal)
    TextView tvEmal;
    private LoginActivity instance;
    private int type = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        initView();
    }

    private void initView() {

    }

    @OnClick({R.id.iv_show, R.id.user_agreement, R.id.ll_login, R.id.tv_register,
            R.id.tv_forgetpass,R.id.tv_phone, R.id.tv_emal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_show:  //显隐密码
                showOrHide(etPassword, ivShow);
                break;
            case R.id.user_agreement:
                break;
            case R.id.ll_login:
                String mPhone = etPhone.getText().toString().trim();
                String mPassword = etPassword.getText().toString().trim();
                if (type ==1){
                    if (checkRegister(instance, mPhone)) {
                        if (!mPassword.equals("")) {
                            getLogin(mPhone, mPassword);
                        } else {
                            ToastLong(instance, "密码不能为空！");
                        }
                    }
                }else if (type==2){
                    if (!mPassword.equals("")) {
                        getLoginEmailData(mPhone, mPassword);
                    } else {
                        ToastLong(instance, "密码不能为空！");
                    }
                }

//
                break;
            case R.id.tv_register:   //注册
                jumpToActivity(RegisterActivity.class);
                break;
            case R.id.tv_forgetpass:  //忘记密码
                Intent forgrtIntent = new Intent(instance, ForgetPassActivity.class);
                startActivity(forgrtIntent);
                break;
            case R.id.tv_phone:   //手机号登录
                type = 1;
                etPhone.setHint("手机号");
                tvEmal.setTextColor(getResources().getColorStateList(R.color.color_888888));
                tvPhone.setTextColor(getResources().getColorStateList(R.color.white));
                break;
            case R.id.tv_emal:    //邮箱登录
                type = 2;
                etPhone.setHint("邮箱");
                tvPhone.setTextColor(getResources().getColorStateList(R.color.color_888888));
                tvEmal.setTextColor(getResources().getColorStateList(R.color.white));
                break;
        }
    }


    /**
     * 手机号登录
     */
    private void getLogin(String mPhone, String password) {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getLoginInterface(mPhone, password, application)
                .compose(this.<LoginBean>bindToLifecycle())
                .compose(SchedulerTransformer.<LoginBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    protected void onSuccess(LoginBean mLoginBean) {
                        if (mLoginBean != null) {
                            if (mLoginBean.getCode() == 10086) {
                                ToastShort(instance, mLoginBean.getMessage());
                                SharedPreferencesUtility.setAccessToken(instance, "bearer " + mLoginBean.getData().getAccess_token());
//                                ToastLong(instance,"bearer "+);
                                Log.e("TAG", "assess_token=====" + "bearer " + mLoginBean.getData().getAccess_token());
                                SharedPreferencesUtility.setUserId(instance, mLoginBean.getData().getUser_id());
                                SharedPreferencesUtility.setUserPhone(instance,mLoginBean.getData().getPhone());
                                SharedPreferencesUtility.setAvatar(instance,mLoginBean.getData().getPortrait());
                                SharedPreferencesUtility.setLevel(instance,mLoginBean.getData().getLevel()+"");
                                jumpToActivity(MainActivity.class);
                            } else {
                                ToastShort(instance, mLoginBean.getMessage());
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
     * 邮箱登录
     */
    private void getLoginEmailData(String mPhone, String password) {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getLoginEmail(mPhone, password, application)
                .compose(this.<EmailBean>bindToLifecycle())
                .compose(SchedulerTransformer.<EmailBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<EmailBean>() {
                    @Override
                    protected void onSuccess(EmailBean mEmailBean) {
                        if (mEmailBean != null) {
                            if (mEmailBean.getCode() == 10086) {
                                ToastShort(instance, mEmailBean.getMessage());
                                SharedPreferencesUtility.setAccessToken(instance, "bearer " + mEmailBean.getData().getAccess_token());
//                                ToastLong(instance,"bearer "+);
                                Log.e("TAG", "assess_token=====" + "bearer " + mEmailBean.getData().getAccess_token());
                                SharedPreferencesUtility.setUserId(instance, mEmailBean.getData().getUser_id());
                                SharedPreferencesUtility.setUserPhone(instance,mEmailBean.getData().getEmail());
                                SharedPreferencesUtility.setAvatar(instance,mEmailBean.getData().getPortrait());
                                SharedPreferencesUtility.setLevel(instance,mEmailBean.getData().getLevel()+"");
                                jumpToActivity(MainActivity.class);
                            } else {
                                ToastShort(instance, mEmailBean.getMessage());
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
     * 密码显示或隐藏 （切换）
     */
    private void showOrHide(EditText etPassword, ImageView show) {
        //记住光标开始的位置
        int pos = etPassword.getSelectionStart();
        if (etPassword.getInputType() != (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {//隐藏密码
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            show.setBackgroundResource(R.mipmap.icon_by_1);
        } else {//显示密码
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            show.setBackgroundResource(R.mipmap.icon_zy_1);
        }
        etPassword.setSelection(pos);

    }


}
