package com.example.project.iwdproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project.iwdproject.Beans.RecordBean;
import com.example.project.iwdproject.Listeners.OnRecyclerViewItemDeClickListener;
import com.example.project.iwdproject.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {



    private Context mContext;
    private OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener;
    private List<RecordBean.DataBean> mRecordData;

    public BillAdapter(Context mContext, List<RecordBean.DataBean> mRecordData, OnRecyclerViewItemDeClickListener OnRecyclerViewItemDeClickListener) {
        this.mContext = mContext;
        this.mRecordData = mRecordData;
        this.OnRecyclerViewItemDeClickListener = OnRecyclerViewItemDeClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_bill, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int CoinType = mRecordData.get(i).getToken();
        if (CoinType == 1){
            viewHolder.tvName.setText("IWD");
        }else {
            viewHolder.tvName.setText("USDT");
        }
        viewHolder.tvTime.setText(mRecordData.get(i).getCreated_at());
        viewHolder.tvZhuan.setText(mRecordData.get(i).getAmount()+"");


    }

    @Override
    public int getItemCount() {
        return mRecordData != null ? mRecordData.size() : 0;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_zhuan)
        TextView tvZhuan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
