package com.example.project.iwdproject.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project.iwdproject.R;
import com.mchsdk.paysdk.mylibrary.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoinLeaseActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.ll_jilu)
    LinearLayout llJilu;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.iv_choose)
    ImageView ivChoose;
    @BindView(R.id.iv_coin)
    ImageView ivCoin;
    @BindView(R.id.tv_coinname)
    TextView tvCoinname;
    @BindView(R.id.tv_choose1)
    ImageView tvChoose1;
    @BindView(R.id.iv_coin1)
    ImageView ivCoin1;
    @BindView(R.id.tv_coinname1)
    TextView tvCoinname1;
    @BindView(R.id.ll_lease)
    LinearLayout llLease;
    private CoinLeaseActivity insatnce;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coinlease);
        ButterKnife.bind(this);
        insatnce = this;
        addActivity(insatnce);
        initView();
    }


    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("租赁");
    }

    @OnClick({R.id.iv_left, R.id.rl_back, R.id.ll_jilu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.rl_back:
                finishActivity(insatnce);
                break;
            case R.id.ll_jilu:   //记录
                Intent RecordIntent = new Intent(insatnce,RecordActivity.class);
                startActivity(RecordIntent);
                break;
        }
    }
}
