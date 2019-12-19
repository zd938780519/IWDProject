package com.example.project.iwdproject.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project.iwdproject.R;
import com.mchsdk.paysdk.mylibrary.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferActivity extends BaseActivity {
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
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.user_agreement)
    TextView userAgreement;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.ll_nextbtn)
    LinearLayout llNextbtn;
    private TransferActivity instance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        instance = this;
        addActivity(instance);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("转账");
    }


    @OnClick({R.id.iv_left, R.id.tv_left, R.id.rl_back,R.id.ll_nextbtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.tv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.ll_nextbtn:
                String mAccount = etAccount.getText().toString().trim();
                if (!mAccount.equals("")){
                    Intent TransferNumIntent = new Intent(instance,TransferNumActivity.class);
                    TransferNumIntent.putExtra("mAccount",mAccount);
                    startActivity(TransferNumIntent);
                }else {
                    ToastLong(instance,"账户不能为空！");
                }
                break;
        }
    }
}
