package com.example.project.iwdproject.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project.iwdproject.Dialogs.ChooseDialogs;
import com.example.project.iwdproject.R;
import com.mchsdk.paysdk.mylibrary.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealNameActivity extends BaseActivity {
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
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.rl_idcard)
    RelativeLayout rlIdcard;
    @BindView(R.id.et_idcardnum)
    EditText etIdcardnum;
    private RealNameActivity instance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realname);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        initView();
    }


    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("实名认证");
    }

    @OnClick({R.id.rl_back, R.id.tv_right, R.id.rl_right,R.id.rl_idcard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.tv_right:
                break;
            case R.id.rl_right:

                break;
            case R.id.rl_idcard:     //身份证
                FragmentManager fm = getSupportFragmentManager();
                ChooseDialogs mChooseDialogs = new ChooseDialogs();
                Bundle bundle = new Bundle();
//                bundle.putString("Number", mAddCircleBean.getData().getNumber());   //奖励个数
//                bundle.putString("coinname", mAddCircleBean.getData().getName());   //币名称
//                bundle.putString("circle_id",circle_id);
//                bundle.putInt("type", 10);   //状态
                mChooseDialogs.setArguments(bundle);
                mChooseDialogs.show(fm, "mChooseDialogs");
                break;
        }
    }
}
