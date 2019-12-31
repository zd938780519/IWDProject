package com.example.project.iwdproject.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.iwdproject.Beans.CodeBean;
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

public class ForgetPassActivity extends BaseActivity {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
//    @BindView(R.id.tv_choose)
//    TextView tvChoose;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_setcode)
    TextView tvSetcode;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.user_agreement)
    TextView userAgreement;
    @BindView(R.id.ll_next)
    LinearLayout llNext;
    private ForgetPassActivity instance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        initView();
    }


    private void initView() {

    }


    @OnClick({R.id.ll_back, R.id.tv_setcode, R.id.ll_next,R.id.user_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finishActivity(instance);

                break;
            case R.id.tv_setcode:   //获得验证码
                String mPhone = etPhone.getText().toString().trim();
                if (checkRegister(instance, mPhone)) {
                    if (!mPhone.equals("")) {
                        getCode(mPhone);
                        TimeCountUtil timeCount = new TimeCountUtil(instance, 60000, 1000, tvSetcode);
                        timeCount.start();
                    }
                }
                break;
            case R.id.ll_next:   //设置新密码
                if (check.isChecked() ==true) {
                    String mPhone1 = etPhone.getText().toString().trim();
                    String mCode = etCode.getText().toString().trim();
                    if (checkRegister(instance, mPhone1)) {
                        if (!mCode.equals("")) {
                            Intent setUpPassWordIntent = new Intent(instance, SetUpNewPassWordActivity.class);
                            setUpPassWordIntent.putExtra("mPhone", mPhone1);
                            setUpPassWordIntent.putExtra("mCode", mCode);
                            startActivity(setUpPassWordIntent);
                        }
                    }
                }else {
                    ToastLong(instance,"请确定已阅读协议！");
                }

                break;
            case R.id.user_agreement:
                Intent AgreementIntent = new Intent(instance,AgreementActivity.class);
                startActivity(AgreementIntent);
                break;
        }
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

}
