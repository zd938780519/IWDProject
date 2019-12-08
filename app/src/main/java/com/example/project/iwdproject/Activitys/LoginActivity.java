package com.example.project.iwdproject.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.iwdproject.R;
import com.mchsdk.paysdk.mylibrary.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private LoginActivity instance;

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

    @OnClick({R.id.iv_show, R.id.user_agreement, R.id.ll_login,R.id.tv_register,R.id.tv_forgetpass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_show:
                break;
            case R.id.user_agreement:
                break;
            case R.id.ll_login:
                break;
            case R.id.tv_register:   //注册
                jumpToActivity(RegisterActivity.class);
                break;
            case R.id.tv_forgetpass:  //忘记密码
                Intent forgrtIntent = new Intent(instance,ForgetPassActivity.class);
                startActivity(forgrtIntent);
                break;
        }
    }
}
