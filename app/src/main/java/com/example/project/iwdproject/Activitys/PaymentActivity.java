package com.example.project.iwdproject.Activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project.iwdproject.Dialogs.ChooseDialogs;
import com.example.project.iwdproject.Dialogs.SetMoneyDilogs;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.Utils.PopupWindow.CommonPopupWindow;
import com.mchsdk.paysdk.mylibrary.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentActivity extends BaseActivity implements CommonPopupWindow.ViewInterface{
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
    @BindView(R.id.iv_twocode)
    ImageView ivTwocode;
    @BindView(R.id.tv_setmoney)
    TextView tvSetmoney;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.rl_collection)
    RelativeLayout rlCollection;
    @BindView(R.id.tv_zhang)
    ImageView tvZhang;
    @BindView(R.id.rl_zhangdan)
    RelativeLayout rlZhangdan;
    private PaymentActivity instance;

    private CommonPopupWindow popupWindow;
    private TextView tv_usdt;
    private TextView tv_iwd;
    private ImageView iv_sure;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        initView();
    }


    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("收付款码");
    }


    @OnClick({R.id.iv_left, R.id.rl_back, R.id.tv_tilte,R.id.tv_setmoney,R.id.rl_collection,R.id.rl_zhangdan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
            case R.id.tv_tilte:
                break;
            case R.id.tv_setmoney:  //设置金额
                FragmentManager fm = getSupportFragmentManager();
                SetMoneyDilogs mSetMoneyDilogs = new SetMoneyDilogs();
                Bundle bundle = new Bundle();
//                bundle.putString("Number", mAddCircleBean.getData().getNumber());   //奖励个数
//                bundle.putString("coinname", mAddCircleBean.getData().getName());   //币名称
//                bundle.putString("circle_id",circle_id);
//                bundle.putInt("type", 10);   //状态
                mSetMoneyDilogs.setArguments(bundle);
                mSetMoneyDilogs.show(fm, "mChooseDialogs");
                break;
            case R.id.rl_collection:   //选择币种
                showDifficultDownPop(rlCollection);
                break;
            case R.id.rl_zhangdan:    //账单
                Intent mBillIntent = new Intent(instance,BillActivity.class);
                startActivity(mBillIntent);
                break;
        }
    }





    /**
     *  难度弹窗
     * @param view
     */
    private void showDifficultDownPop(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.difficult_popup_down)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setViewOnclickListener(instance)
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(view);
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        //获得PopupWindow布局里的View
        switch (layoutResId) {
            case R.layout.difficult_popup_down:
                tv_usdt  = view.findViewById(R.id.tv_usdt);
                tv_iwd = view.findViewById(R.id.tv_iwd);
                iv_sure = view.findViewById(R.id.iv_sure);
                iv_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                tv_usdt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_usdt.setTextColor(getResources().getColor(R.color.color_6362EB));
                        tv_iwd.setTextColor(getResources().getColor(R.color.white));
                    }
                });
                tv_iwd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_usdt.setTextColor(getResources().getColor(R.color.white));
                        tv_iwd.setTextColor(getResources().getColor(R.color.color_6362EB));
                    }
                });
//                mTineFour = view.findViewById(R.id.tv_timefour);
//                mTineFive = view.findViewById(R.id.tv_timefive);
//                mTineOne.setOnClickListener(instance);
//                mTineTwo.setOnClickListener(instance);
//                mTineThree.setOnClickListener(instance);
//                mTineFour.setOnClickListener(instance);
//                mTineFive.setOnClickListener(instance);
//                RecyclerView recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
//                recycle_view.setLayoutManager(new GridLayoutManager(this, 3));
//                PopupAdapter mAdapter = new PopupAdapter(this);
//                recycle_view.setAdapter(mAdapter);
//                mAdapter.setOnItemClickListener(new MyOnclickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        toast("position is " + position);
//                        if (popupWindow != null) {
//                            popupWindow.dismiss();
//                        }
//                    }
//                });
                break;
        }
    }
}
