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

import com.example.project.iwdproject.Beans.CodeBean;
import com.example.project.iwdproject.Beans.RegisterBean;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.TimeCountUtil;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

import static com.example.project.iwdproject.Utils.ValidationUntils.checkRegister;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_nextpassword)
    EditText etNextpassword;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_setcode)
    TextView tvSetcode;
    @BindView(R.id.invitation_code)
    EditText invitationCode;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.user_agreement)
    TextView userAgreement;
    @BindView(R.id.ll_register)
    LinearLayout llRegister;
    @BindView(R.id.iv_show)
    ImageView ivShow;
    @BindView(R.id.iv_showt)
    ImageView ivShowt;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_emal)
    TextView tvEmal;
    private RegisterActivity instance;
    private int type = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
//        EyesUtils.setImmersionStateMode(instance);  //实现沉浸
        initView();
    }


    private void initView() {

    }

    @OnClick({R.id.ll_back, R.id.et_phone, R.id.tv_setcode,
            R.id.ll_register, R.id.iv_show, R.id.iv_showt,R.id.tv_phone,R.id.tv_emal,R.id.user_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finishActivity(instance);
                break;
            case R.id.et_phone:


                break;
            case R.id.tv_setcode:   //获取验证码
                String mPhone = etPhone.getText().toString().trim();
                if (type ==1){
                    if (checkRegister(instance, mPhone)) {
                        if (!mPhone.equals("")) {
                            getCode(mPhone);
                            TimeCountUtil timeCount = new TimeCountUtil(instance, 60000, 1000, tvSetcode);
                            timeCount.start();
                        }
                    }
                }else if (type ==2){
                    if (!mPhone.equals("")) {
                        getEmailCode(mPhone);
                        TimeCountUtil timeCount = new TimeCountUtil(instance, 60000, 1000, tvSetcode);
                        timeCount.start();
                    }
                }

                break;
            case R.id.ll_register:   //注册账号
                String mPhone1 = etPhone.getText().toString().trim();
                String mPassword = etPassword.getText().toString().trim();
                String mNextpassword = etNextpassword.getText().toString().trim();
                String mCode = etCode.getText().toString().trim();
                String mInvitationCode = invitationCode.getText().toString().trim();
                Log.e("TAG","email==="+mPhone1);
                if (check.isChecked() ==true) {
                    if (type == 1) {   //手机号注册
                        if (checkRegister(instance, mPhone1)) {
                            if (!mPassword.equals("") && !mNextpassword.equals("")) {
                                if (mPassword.equals(mNextpassword)) {
                                    if (!mCode.equals("")) {
                                        if (!mInvitationCode.equals("")) {
                                            getRegister(mPhone1, mPassword, mCode, mInvitationCode);
                                        } else {
                                            ToastLong(instance, "邀请码不能为空！");
                                        }
                                    } else {
                                        ToastLong(instance, "验证码不能为空！");
                                    }
                                } else {
                                    ToastLong(instance, "确认密码是否相同！");
                                }
                            } else {
                                ToastLong(instance, "密码不能为空！");
                            }
                        }
                    } else if (type == 2) {   //邮箱注册
                        if (!mPassword.equals("") && !mNextpassword.equals("")) {
                            if (mPassword.equals(mNextpassword)) {
                                if (!mCode.equals("")) {
                                    if (!mInvitationCode.equals("")) {
                                        getEmailRegister(mPhone1, mPassword, mCode, mInvitationCode);
                                    } else {
                                        ToastLong(instance, "邀请码不能为空！");
                                    }
                                } else {
                                    ToastLong(instance, "验证码不能为空！");
                                }
                            } else {
                                ToastLong(instance, "确认密码是否相同！");
                            }
                        } else {
                            ToastLong(instance, "密码不能为空！");
                        }
                    }
                }else{
                    ToastLong(instance,"请确定已阅读协议！");
                }

                break;
            case R.id.iv_show:   //隐藏显示
                showOrHide(etPassword, ivShow);

                break;
            case R.id.iv_showt:   //隐藏显示
                showOrHide(etNextpassword, ivShowt);

                break;
            case R.id.tv_phone:   //手机号注册
                type = 1;
                etPhone.setHint("手机号");
                tvEmal.setTextColor(getResources().getColorStateList(R.color.color_888888));
                tvPhone.setTextColor(getResources().getColorStateList(R.color.white));
                break;
            case R.id.tv_emal:    //邮箱注册
                type = 2;
                etPhone.setHint("邮箱");
                tvPhone.setTextColor(getResources().getColorStateList(R.color.color_888888));
                tvEmal.setTextColor(getResources().getColorStateList(R.color.white));
                break;
            case R.id.user_agreement:
                Intent AgreementIntent = new Intent(instance,AgreementActivity.class);
                startActivity(AgreementIntent);
                break;
        }
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


    /**
     * 获取验证码
     */
    private void getCode(String mPhone) {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getVerificationCode(mPhone, application)
                .compose(this.<CodeBean>bindToLifecycle())
                .compose(SchedulerTransformer.<CodeBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<CodeBean>() {
                    @Override
                    protected void onSuccess(CodeBean mCodeBean) {
                        if (mCodeBean != null) {
                            if (mCodeBean.getCode() == 10086) {
//                                ToastShort(instance,mCodeBean.getMessage());

                            } else {
                                ToastShort(instance, mCodeBean.getMessage());
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
     * 邮箱获取验证码
     */
    private void getEmailCode(String mPhone) {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getEailCode(mPhone, application)
                .compose(this.<CodeBean>bindToLifecycle())
                .compose(SchedulerTransformer.<CodeBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<CodeBean>() {
                    @Override
                    protected void onSuccess(CodeBean mCodeBean) {
                        if (mCodeBean != null) {
                            if (mCodeBean.getCode() == 10086) {
//                                ToastShort(instance,mCodeBean.getMessage());

                            } else {
                                ToastShort(instance, mCodeBean.getMessage());
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
     * 手机号注册账号
     */
    private void getRegister(String mPhone, String password, String code, String invitationNum) {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getRegisterCode(mPhone, password, code, invitationNum, application)
                .compose(this.<RegisterBean>bindToLifecycle())
                .compose(SchedulerTransformer.<RegisterBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<RegisterBean>() {
                    @Override
                    protected void onSuccess(RegisterBean mRegisterBean) {
                        if (mRegisterBean != null) {
                            if (mRegisterBean.getCode() == 10086) {
                                ToastShort(instance, mRegisterBean.getMessage());
//                                mCode = mCodeBean.getData().getCode();

                                finishActivity(instance);
//                                SharedPreferencesUtility.setUserId(instance,"");
                            } else {
                                ToastShort(instance, mRegisterBean.getMessage());
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
     * 邮箱注册账号
     */
    private void getEmailRegister(String mPhone, String password, String code, String invitationNum) {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getRegisterEmail(mPhone, password, code, invitationNum, application)
                .compose(this.<RegisterBean>bindToLifecycle())
                .compose(SchedulerTransformer.<RegisterBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<RegisterBean>() {
                    @Override
                    protected void onSuccess(RegisterBean mRegisterBean) {
                        if (mRegisterBean != null) {
                            if (mRegisterBean.getCode() == 10086) {
                                ToastShort(instance, mRegisterBean.getMessage());
//                                mCode = mCodeBean.getData().getCode();

                                finishActivity(instance);
//                                SharedPreferencesUtility.setUserId(instance,"");
                            } else {

                                ToastShort(instance, mRegisterBean.getMessage());
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


}
