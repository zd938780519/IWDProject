package com.example.project.iwdproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project.iwdproject.Activitys.AgreementActivity;
import com.example.project.iwdproject.Activitys.LoginActivity;
import com.example.project.iwdproject.Activitys.LoginPassWordActivity;
import com.example.project.iwdproject.Activitys.PaypassActivity;
import com.example.project.iwdproject.Activitys.PleaseCodeActivity;
import com.example.project.iwdproject.Activitys.RealNameActivity;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.project.iwdproject.Fragment.HomePageFragment.FRAD_TITLE;

public class SelfFragment extends BaseFragment {

    @BindView(R.id.nothead_iamge)
    CircularImageView notheadIamge;
    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_realname)
    RelativeLayout rlRealname;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.rl_pleasecode)
    RelativeLayout rlPleasecode;



    @BindView(R.id.iv_5)
    ImageView iv5;
    @BindView(R.id.iv_back5)
    ImageView ivBack5;
    @BindView(R.id.rl_loginpass)
    RelativeLayout rlLoginpass;
    @BindView(R.id.iv_6)
    ImageView iv6;
//    @BindView(R.id.iv_back6)
//    ImageView ivBack6;
    @BindView(R.id.rl_paypass)
    RelativeLayout rlPaypass;
//    @BindView(R.id.iv_7)
//    ImageView iv7;
//    @BindView(R.id.rl_bindpone)
//    RelativeLayout rlBindpone;
    @BindView(R.id.iv_8)
    ImageView iv8;
    @BindView(R.id.rl_agreement)
    RelativeLayout rlAgreement;
    @BindView(R.id.iv_9)
    ImageView iv9;
    @BindView(R.id.iv_back9)
    ImageView ivBack9;
    @BindView(R.id.rl_update)
    RelativeLayout rlUpdate;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    Unbinder unbinder;
    @BindView(R.id.tv_userid)
    TextView tvUserid;
    Unbinder unbinder1;
    @BindView(R.id.tv_assecct)
    TextView tvAssecct;
    @BindView(R.id.tv_leavel)
    TextView tvLeavel;
    private Context instance;

    public static SelfFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(FRAD_TITLE, title);
        SelfFragment fragment = new SelfFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_self;
    }

    @Override
    protected void initView() {
        instance = getActivity();
        String userheaderimage = SharedPreferencesUtility.getAvatar(instance);
        int userID = SharedPreferencesUtility.getUserId(instance);
        String mAssecct = SharedPreferencesUtility.getUserPhone(instance);
        String mLeavel = SharedPreferencesUtility.getLevel(instance);
        tvUserid.setText("ID:" + userID);
        tvAssecct.setText("账号:"+mAssecct);
        tvLeavel.setText("会员等级:LV."+ mLeavel);

        Glide.with(instance).load(userheaderimage).placeholder(R.mipmap.img_avatar_1).into(notheadIamge);
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
        unbinder1.unbind();
    }

    @OnClick({R.id.rl_pleasecode, R.id.rl_loginpass, R.id.rl_paypass,
            R.id.rl_agreement, R.id.rl_update, R.id.rl_realname, R.id.ll_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_pleasecode:   //邀请码
                Intent PleaseCodeIntent = new Intent(instance, PleaseCodeActivity.class);
                startActivity(PleaseCodeIntent);

                break;

            case R.id.rl_loginpass:   //修改登录密码
                Intent LoginPassWordIntent = new Intent(instance, LoginPassWordActivity.class);
                startActivity(LoginPassWordIntent);
                break;
            case R.id.rl_paypass:    //修改支付密码
                Intent mPayPassIntent = new Intent(instance, PaypassActivity.class);
                startActivity(mPayPassIntent);
                break;
//            case R.id.rl_bindpone:
//                break;
            case R.id.rl_agreement:
                Intent AgreementIntent = new Intent(instance,AgreementActivity.class);
                startActivity(AgreementIntent);
                break;
            case R.id.rl_update:
                break;
            case R.id.rl_realname:   //实名认证
                Intent RealNameIntent = new Intent(instance, RealNameActivity.class);
                startActivity(RealNameIntent);
                break;
            case R.id.ll_login:
                SharedPreferencesUtility.clearAccessToken(instance);
                SharedPreferencesUtility.clearUserId(instance);
                SharedPreferencesUtility.clearLevel(instance);
                SharedPreferencesUtility.clearUserPhone(instance);
                SharedPreferencesUtility.clearAvatar(instance);
                Intent loginIntent = new Intent(instance, LoginActivity.class);
                startActivity(loginIntent);
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
