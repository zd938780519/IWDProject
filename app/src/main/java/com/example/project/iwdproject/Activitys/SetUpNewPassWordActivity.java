package com.example.project.iwdproject.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.project.iwdproject.Beans.CodeBean;
import com.example.project.iwdproject.Beans.ModifyPassWordBean;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

public class SetUpNewPassWordActivity extends BaseActivity {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_show)
    ImageView ivShow;
    @BindView(R.id.et_nextpassword)
    EditText etNextpassword;
    @BindView(R.id.iv_shownext)
    ImageView ivShownext;
    @BindView(R.id.ll_sure)
    LinearLayout llSure;
    private SetUpNewPassWordActivity instance;
    private String mPhone;
    private String mCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setuppassword);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        initView();
    }


    private void initView() {
        mPhone = getIntent().getStringExtra("mPhone");
        mCode = getIntent().getStringExtra("mCode");
    }

    @OnClick({R.id.ll_back, R.id.iv_shownext, R.id.ll_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finishActivity(instance);
                break;
            case R.id.iv_shownext:
                break;
            case R.id.ll_sure:   //确定修改新密码
                String mPassword = etPassword.getText().toString().trim();
                String mNextpassword = etNextpassword.getText().toString().trim();
                if (!mPassword.equals("") && !mNextpassword.equals("")){
                   if (mPassword.equals(mNextpassword)){
                       SetNewPassWord(mPhone,mPassword,mCode);
                   }else {
                       ToastLong(instance,"确认密码是否相同！");
                   }
                }else {
                    ToastLong(instance,"密码不能为空！");
                }

                break;
        }
    }





    /**
     * 修改新密码
     */
    private void SetNewPassWord(String mPhone,String mPassword,String mCode) {
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getModifyPass(mPhone,mPassword,mCode ,application)
                .compose(this.<ModifyPassWordBean>bindToLifecycle())
                .compose(SchedulerTransformer.<ModifyPassWordBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<ModifyPassWordBean>() {
                    @Override
                    protected void onSuccess(ModifyPassWordBean mModifyPassWordBean) {
                        if (mModifyPassWordBean != null) {
                            if (mModifyPassWordBean.getCode() == 10086) {
                                ToastShort(instance,mModifyPassWordBean.getMessage());

                            } else {
                                ToastShort(instance, mModifyPassWordBean.getMessage());
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
