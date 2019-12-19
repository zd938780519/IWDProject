package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project.iwdproject.Activitys.RentalIncomeActivity;
import com.example.project.iwdproject.Beans.MyProfitBean;
import com.example.project.iwdproject.Beans.ProfitLogBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyIncomeAdapter extends RecyclerView.Adapter<MyIncomeAdapter.ViewHolder> {

  private Context mContext;
    private OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener;
    private List<ProfitLogBean.DataBean> mProfitLogData;
    private String coinname = "IWD";


    public MyIncomeAdapter(Context mContext,List<ProfitLogBean.DataBean> mProfitLogData, OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener) {
        this.mContext = mContext;
        this.mProfitLogData = mProfitLogData;
        this.OnRecyclerViewItemDeClickListener = OnRecyclerViewItemDeClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_myoncome, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int type = mProfitLogData.get(i).getSource_id();
//        int token = mProfitLogData.get(i).getToken();
        Log.e("TAG","type===="+type);
        Log.e("TAG","getAmount===="+mProfitLogData.get(i).getAmount()+"IWD");
        if (type == 1){
            viewHolder.tvType.setText("充值");
        }else if (type == 2){
            viewHolder.tvType.setText("USDT收益");
        }else if (type == 3){
            viewHolder.tvType.setText("IWD收益");
        }else if (type == 4){
            viewHolder.tvType.setText("提现");
        }else if (type == 5){
            viewHolder.tvType.setText("邀请奖励");
        }

            viewHolder.tvNumber.setText(mProfitLogData.get(i).getAmount()+"IWD");



        viewHolder.rlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RentalIncomeIntent = new Intent(mContext,RentalIncomeActivity.class);
                mContext.startActivity(RentalIncomeIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProfitLogData != null ? mProfitLogData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.iv_back2)
        ImageView ivBack2;
//        @BindView(R.id.tv_benefitsnum)
//        TextView tvBenefitsnum;
        @BindView(R.id.rl_layout)
        RelativeLayout rlLayout;
        private Context mContext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
