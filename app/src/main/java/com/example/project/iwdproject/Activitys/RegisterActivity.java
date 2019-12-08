package com.example.project.iwdproject.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.iwdproject.R;
import com.mchsdk.paysdk.mylibrary.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private RegisterActivity instance;

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

    @OnClick({R.id.ll_back, R.id.et_phone, R.id.tv_setcode, R.id.ll_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finishActivity(instance);
                break;
            case R.id.et_phone:
                break;
            case R.id.tv_setcode:
                break;
            case R.id.ll_register:
                break;
        }
    }
}
