package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.iwdproject.Activitys.LeaseMessageActivity;
import com.example.project.iwdproject.Beans.MyBalanceListBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaseTwoAdapter extends RecyclerView.Adapter<LeaseTwoAdapter.ViewHolder> {


    private Context mContext;
    private OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener;
    private List<MyBalanceListBean.DataBean.ListBean> mMyBalanceListData;

    public LeaseTwoAdapter(Context mContext,List<MyBalanceListBean.DataBean.ListBean> mMyBalanceListData, OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener) {
        this.mContext = mContext;
        this.mMyBalanceListData = mMyBalanceListData;
        this.OnRecyclerViewItemDeClickListener = OnRecyclerViewItemDeClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_twolease, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final int coinId = mMyBalanceListData.get(i).getId();
        viewHolder.tvCoinname.setText(mMyBalanceListData.get(i).getName());
        viewHolder.tvCoinprice.setText(mMyBalanceListData.get(i).getNum());
        viewHolder.tvPrice.setText("$"+mMyBalanceListData.get(i).getPrice());
        viewHolder.tvTwoprice.setText("$"+mMyBalanceListData.get(i).getTotal());
        viewHolder.llAlllayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LeaseMessageIntent = new Intent(mContext,LeaseMessageActivity.class);
                LeaseMessageIntent.putExtra("coinId",coinId);
                mContext.startActivity(LeaseMessageIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
//        return 3;
        return mMyBalanceListData != null ? mMyBalanceListData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_iamge)
        ImageView ivIamge;
        @BindView(R.id.tv_coinname)
        TextView tvCoinname;
        @BindView(R.id.tv_coinprice)
        TextView tvCoinprice;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_twoprice)
        TextView tvTwoprice;
        @BindView(R.id.ll_alllayout)
        LinearLayout llAlllayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
