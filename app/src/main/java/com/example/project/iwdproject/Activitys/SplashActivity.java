package com.example.project.iwdproject.Activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.project.iwdproject.MainActivity;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.Utils.EyesUtils;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created By zdd on 2018/10/26
 */
public class SplashActivity extends BaseActivity {
    private static final int TIME = 3000;
    private static final int GO_HOME = 1;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    private SplashActivity instance;
    private String mAccessToken;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        instance = this;
        EyesUtils.setImmersionStateMode(instance);  //实现沉浸
        ButterKnife.bind(this);
        addActivity(instance);
        initData();
    }


    private void initData() {
        handler.sendEmptyMessageDelayed(GO_HOME, TIME);
    }


    /**
     * 跳转到聊天界面
     */
    private void goHome() {
        mAccessToken = SharedPreferencesUtility.getAccessToken(instance);
        if (!mAccessToken.equals("")){
            jumpToActivity(MainActivity.class);
        }else{
            jumpToActivity(LoginActivity.class);
        }

        finishActivity(instance);
    }
}
