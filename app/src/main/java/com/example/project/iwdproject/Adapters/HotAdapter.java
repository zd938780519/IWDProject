package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project.iwdproject.Beans.MarketBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {

    private Context mContext;
    private OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener;
    private List<MarketBean.DataBean> mMarketData = new ArrayList<>();

    public HotAdapter(Context mContext, List<MarketBean.DataBean> mMarketData, OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener) {
        this.mContext = mContext;
        this.mMarketData = mMarketData;
        this.OnRecyclerViewItemDeClickListener = OnRecyclerViewItemDeClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_hot, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvCoin.setText(mMarketData.get(i).getName());
        viewHolder.tvZhishu.setText(mMarketData.get(i).getUsd());
        viewHolder.tvZenfu.setText(mMarketData.get(i).getChange()+"%");
        viewHolder.tvPrice.setText("≈"+mMarketData.get(i).getCny()+"CNY");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_coin)
        TextView tvCoin;
        @BindView(R.id.tv_zhishu)
        TextView tvZhishu;
        @BindView(R.id.tv_zenfu)
        TextView tvZenfu;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
