package com.example.project.iwdproject.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.project.iwdproject.R;
import com.mchsdk.paysdk.mylibrary.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    }

    @OnClick({R.id.ll_back, R.id.iv_shownext, R.id.ll_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finishActivity(instance);
                break;
            case R.id.iv_shownext:
                break;
            case R.id.ll_sure:
                break;
        }
    }
}
