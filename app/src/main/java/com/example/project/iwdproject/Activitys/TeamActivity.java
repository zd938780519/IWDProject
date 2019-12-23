package com.example.project.iwdproject.Activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.iwdproject.Adapters.TeamAdapter;
import com.example.project.iwdproject.Beans.TeamBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;
import com.example.project.iwdproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.project.iwdproject.Utils.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

public class TeamActivity extends BaseActivity {
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
    @BindView(R.id.recycle_team)
    RecyclerView recycleTeam;
    @BindView(R.id.tv_allpeople)
    TextView tvAllpeople;
    private TeamActivity instance;
    private String token;
    private List<TeamBean.DataBean.ListBeanX> mListBeanX;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        token = SharedPreferencesUtility.getAccessToken(instance);
        initView();
    }


    /**
     * 我的团队
     */
    private void getMyTeamData() {
//        Log.e("TAG","token====="+token);
        String application = "application/json";
        RetrofitHttpUtil.getApiService()
                .getMyTeam(token, application)
                .compose(this.<TeamBean>bindToLifecycle())
                .compose(SchedulerTransformer.<TeamBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<TeamBean>() {
                    @Override
                    protected void onSuccess(TeamBean mTeamBean) {
                        if (mTeamBean != null) {
                            if (mTeamBean.getCode() == 10086) {
                                mListBeanX = mTeamBean.getData().getList();
                                tvAllpeople.setText(mTeamBean.getData().getTotal_num()+"");
//                                Toast.makeText(instance, mProfitLogBean.getMessage(), Toast.LENGTH_SHORT).show();
//                                mProfitLogData = mProfitLogBean.getData();
                                TeamAdapter mTeamAdapter = new TeamAdapter(instance, mListBeanX, onRecyclerViewItemClickListener);
                                recycleTeam.setAdapter(mTeamAdapter);

                            } else {

                                Toast.makeText(instance, mTeamBean.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    protected void onFailed(HttpResponseException responseException) {
                        super.onFailed(responseException);
//                        ToastShort.showShortToast("网络错误！");
                        Toast.makeText(instance, "error code : " + responseException.getStatus(), Toast.LENGTH_SHORT).show();
//                        ToastShort(instance, "网络有误！");
                    }
                });
    }


    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("团队人数");


        final LinearLayoutManager mTwoLinearLayoutManager = new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        recycleTeam.setLayoutManager(mTwoLinearLayoutManager);
        recycleTeam.setNestedScrollingEnabled(false);
        recycleTeam.addItemDecoration(new DividerItemDecoration(instance, DividerItemDecoration.VERTICAL));   //添加分割线
        //      openredRecycle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式
        recycleTeam.setHasFixedSize(true);


        getMyTeamData();
    }


    private OnRecyclerViewItemDeClickListener onRecyclerViewItemClickListener = new OnRecyclerViewItemDeClickListener() {

        @Override
        public void onRecyclerViewItemClicked(int position, RecyclerView.ViewHolder viewHolder) {

        }

        @Override
        public void onRecyclerViewItemLongClicked(int position, RecyclerView.ViewHolder viewHolder) {

        }

        @Override
        public void onRecyclerViewItemClicked() {

        }


    };


    @OnClick({R.id.iv_left, R.id.tv_left, R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.tv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                break;
        }
    }
}
