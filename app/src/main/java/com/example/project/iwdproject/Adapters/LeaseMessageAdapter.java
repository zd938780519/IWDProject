package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.iwdproject.Beans.MyProfitBeanLogBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaseMessageAdapter extends RecyclerView.Adapter<LeaseMessageAdapter.ViewHolder> {


    private Context mContext;
    private OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener;
    private List<MyProfitBeanLogBean.DataBean> mMyProfitBeanLogData;

    public LeaseMessageAdapter(Context mContext, List<MyProfitBeanLogBean.DataBean> mMyProfitBeanLogData, OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener) {
        this.mContext = mContext;
        this.mMyProfitBeanLogData = mMyProfitBeanLogData;
        this.OnRecyclerViewItemDeClickListener = OnRecyclerViewItemDeClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_leasemessage, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        int type = mMyProfitBeanLogData.get(i).getToken();
        if (type == 1) {
            viewHolder.tvCoinname.setText("WID");
        } else if (type == 2) {
            viewHolder.tvCoinname.setText("USDT");
        }
        viewHolder.tvTime.setText(mMyProfitBeanLogData.get(i).getCreated_at());
        viewHolder.tvNum.setText(mMyProfitBeanLogData.get(i).getAmount()+"");  //租赁数量
        if (mMyProfitBeanLogData.get(i).getStatus() == 1 ){

            viewHolder.tvClose.setText("取消租赁");
            viewHolder.llCancel.setOnClickListener(new View.OnClickListener() {   //取消租赁
                @Override
                public void onClick(View v) {
                    OnRecyclerViewItemDeClickListener.onRecyclerViewItemClicked(i,viewHolder);
                }
            });

        }else if (mMyProfitBeanLogData.get(i).getStatus() == 3){
            viewHolder.tvClose.setText("已取消");
        }

//        viewHolder.tvZengfu.setText(mMyProfitBeanLogData.get(i).getAmount());

    }

    @Override
    public int getItemCount() {
        return mMyProfitBeanLogData != null ? mMyProfitBeanLogData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_coinname)
        TextView tvCoinname;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.ll_cancel)
        LinearLayout llCancel;
        @BindView(R.id.tv_close)
        TextView tvClose;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
